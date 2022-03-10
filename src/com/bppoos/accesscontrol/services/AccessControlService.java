package com.bppoos.accesscontrol.services;

public class AccessControlService {

    private static final char AT_SYMBOL = '@'; // What makes an email address an email address

    /**
     * Take in a user record and authorize it based on email host, IP address and whether two factor auth is being used.
     *
     * @param userRecord String consisting of four comma separated properties: user name, email address, IP address
     *                   and whether two factor auth is enabled.
     * @param emailhost The valid email host an address should contain.
     * @param ipMask    The IP Subnet that the IP Address should start with.
     * @return true if the user record should be allowed access, false otherwise.
     */
    public boolean analyzeEntry(String userRecord, String emailhost, String ipMask)
    {
        // Split the user record string on commas
        String[] properties = userRecord.split(",");

        // if there aren't four entries in the record, the record is invalid
        if(4 != properties.length)
            return false;

        // Check if either there is two factor auth or the IP address is internal
        return  (Boolean.valueOf(properties[3].trim()) || isInteralIP(properties[2].trim(), ipMask)) &&
                // AND check if the email address is from internal email address.
                isEmployeeEmail(properties[1].trim(), emailhost);
    }

    /**
     * Check to see if email is from emailHost address.
     *
     * @param email - the email address to be checked.
     * @param emailHost - the site the address should be from.
     * @return boolean true if email is for correct host, false otherwise.
     */
    private boolean isEmployeeEmail(String email, String emailHost)
    {
        int whereAtIsAt = email.indexOf(AT_SYMBOL);

        // If there is no @, this isn't a valid email. FAIL.
        if(0 > whereAtIsAt)
            return false;

        // Split off everything after the @ and see if it matches the host.
        return emailHost.equalsIgnoreCase(email.substring(whereAtIsAt +1));
    }

    /**
     * Validate that IP is complete and starts with subnet mask.
     * @param ipAddress address to be analyzed
     * @param ipMask    Subnet the address must start with
     * @return true if the address is valid and starts with the subnet mask.
     */
    private boolean isInteralIP(String ipAddress, String ipMask)
    {
        String[] ipParts = ipAddress.split("\\.");

        // validate that ip is four parts.
        if(4 != ipParts.length)
            return false;

        // check if IP address starts with the subnet. Include a period to avoid incidental matches
        // like 127.11 matching 127.1
        return ipAddress.startsWith(ipMask.concat("."));
    }
}
