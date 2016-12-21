package microsoft.powerbi.security;

import java.nio.charset.StandardCharsets;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;
import microsoft.powerbi.ArgumentException;
import microsoft.powerbi.ArgumentNullException;
import microsoft.powerbi.Guard;
import microsoft.powerbi.InvalidOperationException;

/**
 * The Power BI app token used to authenticate with Power BI Embedded services
 */
public class PowerBIToken {

	private static int DefaultExpirationSeconds = 3600;

	private static String DefaultIssuer = "PowerBISDK";

	private static String DefaultAudience = "https://analysis.windows.net/powerbi/api";

	// The token issuer
	private String Issuer;

	// The audience this token is valid for
	private String Audience;

	// Gets or sets a collection of claims associated with this token
	private Claims Claims;

	public String getIssuer() {
		return Issuer;
	}

	public void setIssuer(String issuer) {
		Issuer = issuer;
	}

	public String getAudience() {
		return Audience;
	}

	public void setAudience(String audience) {
		Audience = audience;
	}

	public Claims getClaims() {
		return Claims;
	}

	public void setClaims(Claims claims) {
		Claims = claims;
	}

	public String getAccessKey() {
		return AccessKey;
	}

	public void setAccessKey(String accessKey) {
		AccessKey = accessKey;
	}

	public DateTime getExpiration() {
		return Expiration;
	}

	public void setExpiration(DateTime expiration) {
		Expiration = expiration;
	}

	// Gets or set the access key used to sign the app token
	private String AccessKey;

	// Gets or sets the token expiration. Default expiration is 1 hour
	private DateTime Expiration;

	/**
	 * Represents an access token used to authenticate and authorize against
	 * Power BI Platform services
	 */
	public PowerBIToken() {
		this.Claims = new DefaultClaims();
		this.Issuer = DefaultIssuer;
		this.Audience = DefaultAudience;
		this.Expiration = DateTime.now().plusSeconds(DefaultExpirationSeconds);
		this.InitDefaultClaims();
	}

	/**
	 * Creates a embed token with default expiration used to embed Power BI
	 * components into your own applications
	 * 
	 * @param workspaceCollectionName
	 *            The workspace collection name
	 * @param workspaceId
	 *            The workspace id
	 * @param reportId
	 *            The report id
	 * @param username
	 *            The RLS username
	 * @param roles
	 *            The RLS roels
	 * @return Power BI access Token
	 */
	public static PowerBIToken CreateReportEmbedToken(String workspaceCollectionName, String workspaceId,
			String reportId, String username, List<String> roles) {
		DateTime expires = DateTime.now().plusSeconds(DefaultExpirationSeconds);
		return CreateReportEmbedToken(workspaceCollectionName, workspaceId, reportId, expires, username, roles);
	}

	/**
	 * Creates a embed token with default expiration used to embed Power BI
	 * components into your own applications
	 * 
	 * @param workspaceCollectionName
	 *            The workspace collection name
	 * @param workspaceId
	 *            The workspace id
	 * @param reportId
	 *            The report id
	 * @param expiration
	 *            The token expiration date time.
	 * @param username
	 *            The RLS username
	 * @param roles
	 *            The RLS roels
	 * @return Power BI access Token
	 */
	public static PowerBIToken CreateReportEmbedToken(String workspaceCollectionName, String workspaceId,
			String reportId, DateTime expiration, String username, List<String> roles) {

		Guard.ValidateString(workspaceCollectionName, "workspaceCollectionName");
		Guard.ValidateString(workspaceId, "workspaceId");
		Guard.ValidateString(reportId, "reportId");

		if (expiration.isBeforeNow()) {
			throw new ArgumentException("Expiration must be a date/time in the future", expiration);
		}

		if (roles != null && StringUtils.isBlank(username)) {
			throw new ArgumentException("Cannot have an empty or null Username claim with the non-empty Roles claim");
		}

		PowerBIToken token = new PowerBIToken();
		token.Expiration = expiration;
		token.Claims.put(ClaimTypes.WorkspaceCollectionName, workspaceCollectionName);
		token.Claims.put(ClaimTypes.WorkspaceId, workspaceId);
		token.Claims.put(ClaimTypes.ReportId, reportId);
		if (!StringUtils.isBlank(username)) {
			token.Claims.put(ClaimTypes.Username, username);
			if (roles != null && roles.size() != 0) {
				token.Claims.put(ClaimTypes.Roles, roles.get(0)); // TODO
			}
		}
		return token;
	}

	/**
	 * Generates an app token with the specified claims and signs it the the
	 * configured signing key.
	 * 
	 * @param accessKey
	 *            The access key used to sign the app
	 * @return encoded token
	 */
	public String Generate(String accessKey) {

		if (StringUtils.isBlank(this.AccessKey) && accessKey == null) {
			throw new ArgumentNullException(accessKey);
		}

		this.ValidateToken();

		accessKey = accessKey == null ? this.AccessKey : accessKey;

		byte[] key = accessKey.getBytes(StandardCharsets.UTF_8);
		this.Claims.setIssuer(this.Issuer);
		this.Claims.setAudience(this.Audience);
		this.Claims.setExpiration(this.Expiration.toDate());

		JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JWT").setClaims(this.Claims)
				.signWith(SignatureAlgorithm.HS256, key);

		return builder.compact();
	}

	/**
	 * Returns a JWT token String that represents the current object
	 * 
	 * @param accessKey
	 * @return jwt
	 */
	public String ToString(String accessKey) {
		return Generate(accessKey);
	}

	private void InitDefaultClaims() {
		this.Claims.put(ClaimTypes.Version, "0.2.0");
	}

	private void ValidateToken() {

		if (this.Expiration.isBeforeNow()) {
			throw new InvalidOperationException("Expiration must be set to a date/time in the future");
		}

		if (StringUtils.isBlank(this.Issuer)) {
			throw new InvalidOperationException("Issuer must be set");
		}

		if (StringUtils.isBlank(this.Audience)) {
			throw new InvalidOperationException("Audience must be set");
		}
	}
}
