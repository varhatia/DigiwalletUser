package com.digiwallet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ConnectionProperties extends Properties {

	private static final String PROPERTY_FILE = new File("").getAbsolutePath() + "controller.properties";

    public static final String SERVER_IP = "SERVER_IP";
    public static final String SERVER_PORT = "SERVER_PORT";
    public static final String SSL_ENABLED = "SSL_ENABLED";
    public static final String SERVER_IDLE_TIMEOUT_SECS = "SERVER_IDLE_TIMEOUT_SECS";
    public static final String MAX_RESPONSE_SIZE_IN_BYTES = "MAX_REQUEST_SIZE_IN_BYTES";

    public static final String DB_IP = "DB_IP";
    public static final String DB_PORT = "DB_PORT";
    public static final String DB_PATH = "DB_PATH";
    public static final String DB_USER = "DB_USER";
    public static final String DB_PASSWORD = "DB_PASSWORD";

	private static final String DEFAULT_SERVER_IP = "localhost";
    private static final String DEFAULT_SERVER_PORT = "8000";
    private static final String DEFAULT_SSL_ENABLED = "true";
    private static final String DEFAULT_SERVER_IDLE_TIMEOUT_SECS = "20000";
    public static final String DEFAULT_MAX_RESPONSE_SIZE_IN_BYTES = "10485760";

    private static final String DEFAULT_DB_IP = "localhost";
	private static final String DEFAULT_DB_PORT = "8020";
	private static final String DEFAULT_DB_PATH = "C:\\DigiWallet\\DB\\digiWalletUser";
	private static final String DEFAULT_DB_USER = "controller";
	private static final String DEFAULT_DB_PASSWORD = "";

    private Map<String, String> defaultValues = null;

    public ConnectionProperties()
    {
        try
        {
            FileInputStream stream = new FileInputStream(PROPERTY_FILE);
            load(stream);
        }
        catch(IOException e)
        {
            System.out.println("Unable to load controller.properties. Will use the default values");
        }

        defaultValues = new HashMap<>();
        try
        {
            defaultValues.put(SERVER_IP, InetAddress.getLocalHost().getHostAddress());
        }
        catch(UnknownHostException e)
        {
            defaultValues.put(SERVER_IP, DEFAULT_SERVER_IP);
        }
        defaultValues.put(SERVER_PORT, DEFAULT_SERVER_PORT);
        defaultValues.put(SSL_ENABLED, DEFAULT_SSL_ENABLED);
        defaultValues.put(SERVER_IDLE_TIMEOUT_SECS, DEFAULT_SERVER_IDLE_TIMEOUT_SECS);
        defaultValues.put(MAX_RESPONSE_SIZE_IN_BYTES, DEFAULT_MAX_RESPONSE_SIZE_IN_BYTES);
        defaultValues.put(DB_IP, DEFAULT_DB_IP);
        defaultValues.put(DB_PORT, DEFAULT_DB_PORT);
        defaultValues.put(DB_PATH, DEFAULT_DB_PATH);
        defaultValues.put(DB_USER, DEFAULT_DB_USER);
        defaultValues.put(DB_PASSWORD, DEFAULT_DB_PASSWORD);
    }

    @Override
    public String getProperty(String key)
    {
        String value = super.getProperty(key);
        if(value == null)
        {
            return defaultValues.get(key);
        }
        return value;
    }
}

