package microsoft.powerbi.security;

/**
 * The Power BI supported claim types
 */
interface ClaimTypes {

	/**
	 * The version claim
	 */
	public static String Version = "ver";

	/**
	 * The workspace collection claim
	 */
	public static String WorkspaceCollectionName = "wcn";

	/**
	 * The workspace id claim
	 */
	public static String WorkspaceId = "wid";

	/**
	 * The report id claim
	 */
	public static String ReportId = "rid";

	/**
	 * The RLS username claim
	 */
	public static String Username = "username";

	/**
	 * The RLS roles claim
	 */
	public static String Roles = "roles";
}
