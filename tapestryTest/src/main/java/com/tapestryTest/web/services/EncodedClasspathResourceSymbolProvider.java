package com.tapestryTest.web.services;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Properties;

import org.apache.tapestry5.ioc.Resource;
import org.apache.tapestry5.ioc.internal.util.ClasspathResource;
import org.apache.tapestry5.ioc.internal.util.CollectionFactory;
import org.apache.tapestry5.ioc.internal.util.InternalUtils;
import org.apache.tapestry5.ioc.services.SymbolProvider;

/**
 * @author Inspireso Team
 */
public class EncodedClasspathResourceSymbolProvider implements SymbolProvider {

    private final Resource resource;
    private Charset charset = Charset.defaultCharset();

    private final Map<String, String> properties = CollectionFactory.newCaseInsensitiveMap();

    public EncodedClasspathResourceSymbolProvider(final String path, final Charset charset) {
        this.resource = new ClasspathResource(path);
        this.charset = charset;
        readProperties();
    }

    @SuppressWarnings("Since15")
    private void readProperties() {
        Properties properties = new Properties();

        InputStream inputStream = null;

        try {
            inputStream = resource.openStream();

            properties.load(new InputStreamReader(inputStream, charset));

            inputStream.close();

            inputStream = null;

            for (Map.Entry<Object, Object> entry : properties.entrySet()) {
                String key = entry.getKey().toString();

                this.properties.put(key, properties.getProperty(key));
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } finally {
            InternalUtils.close(inputStream);
        }
    }

    public String valueForSymbol(String symbolName) {
        return properties.get(symbolName);
    }
}