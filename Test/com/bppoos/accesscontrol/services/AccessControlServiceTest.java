package com.bppoos.accesscontrol.services;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AccessControlServiceTest {

    private AccessControlService accessControlService = new AccessControlService();
    private String emailHost = "example.com";
    private String ipMask = "192.168";

    @Test
    public void testSucceedTFA()
    {
        assertTrue(accessControlService.analyzeEntry("JimH, jim@example.com, 31.13.88.35, True", emailHost, ipMask));
    }

    @Test
    public void testSucceedInternalIP()
    {
        assertTrue(accessControlService.analyzeEntry("PamB, pam@example.com, 192.168.0.145, False", emailHost, ipMask));
    }

    @Test
    public void testFailExternalIPNoTfa()
    {
        assertFalse(accessControlService.analyzeEntry("TobyF, toby@example.com, 54.187.103.41, False", emailHost, ipMask));
    }

    @Test
    public void testFailExternalEmail()
    {
        assertFalse(accessControlService.analyzeEntry("BobV, bob.v@gmail.com, 65.121.176.199, True", emailHost, ipMask));
    }

    @Test
    public void testFailBadEmail()
    {
        assertFalse(accessControlService.analyzeEntry("PamB, pamexample.com, 192.168.0.145, False", emailHost, ipMask));
    }

    @Test
    public void testFailBadIpNoTfa()
    {
        assertFalse(accessControlService.analyzeEntry("PamB, pam@example.com, 192.168, False", emailHost, ipMask));
    }

    @Test
    public void testFailIncompleteRecord()
    {
        assertFalse(accessControlService.analyzeEntry("PamB, pam@example.com, 192.168.0.145", emailHost, ipMask));
    }
}
