package com.hs.md;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="hoang281283@gmail.com">Minh Hoang TO</a>
 * @date: 1/25/17
 */
public class HSConfig {

    public final static String JDBC_URL_PARAM_NAME = "jdbcURL";

    public final static String JDBC_DRIVER_PARAM_NAME = "jdbcDriver";

    public final static String JDBC_DB_PRODUCT_PARAM_NAME = "DatabaseProductName";

    public final static String JDBC_DB_MAJOR_VERSION_NAME = "DatabaseMajorVersion";

    private static volatile HSConfig instance;

    private Map<String, Profile> profiles = new HashMap<>();

    private HSConfig() {
        loadConfig("hs.properties");
    }

    public static HSConfig getInstance() {
        if (instance == null) {
            synchronized (HSConfig.class) {
                if (instance == null) {
                    HSConfig tmp = new HSConfig();
                    instance = tmp;
                }
            }
        }

        return instance;
    }

    public Map<String, Profile> getProfiles() {
        return Collections.unmodifiableMap(profiles);
    }

    public Profile getProfile(String name) {
        return profiles.get(name);
    }

    public void loadConfig(String fn) {
        final Map<String, Profile.Builder> pBuilders = new HashMap<>();

        InputStream is = null;
        try {
            is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fn);
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));

            String line;
            String currentAlias = "";
            while ((line = rd.readLine()) != null) {
                line = line.trim();

                if (!line.isEmpty() && line.charAt(0) != '#') {

                    if (line.charAt(0) == '[' && line.endsWith("]")) {
                        currentAlias = line.substring(1, line.length() - 1);
                    } else if (!currentAlias.isEmpty()) {
                        processLine(line, currentAlias, pBuilders);
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (Exception ex) {
                }
            }
        }

        for (Map.Entry<String, Profile.Builder> e : pBuilders.entrySet()) {
            profiles.put(e.getKey(), e.getValue().build());
        }
    }

    private void processLine(final String line, final String alias, final Map<String, Profile.Builder> pBuilders) {
        Profile.Builder b = pBuilders.get(alias);
        if (b == null) {
            b = new Profile.Builder(alias);
            pBuilders.put(alias, b);
        }

        b.addLine(line);
    }
}
