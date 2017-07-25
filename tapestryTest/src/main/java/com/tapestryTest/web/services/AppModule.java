package com.tapestryTest.web.services;

import org.apache.commons.lang.StringUtils;
import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.ioc.Configuration;
import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.OrderedConfiguration;
import org.apache.tapestry5.ioc.ServiceBinder;
import org.apache.tapestry5.ioc.annotations.InjectService;
import org.apache.tapestry5.ioc.annotations.Symbol;
import org.apache.tapestry5.ioc.services.SymbolProvider;
import org.apache.tapestry5.services.javascript.JavaScriptStack;
import org.slf4j.Logger;

import com.google.common.base.Charsets;

public class AppModule {
    public static void contributeJavaScriptStackSource(
            MappedConfiguration<String, JavaScriptStack> configuration) {

    }

    public static void bind(ServiceBinder binder) {

    }

    public static void contributeApplicationDefaults(
            MappedConfiguration<String, Object> configuration) {

        configuration.add(SymbolConstants.GZIP_COMPRESSION_ENABLED, true);
        configuration.add(SymbolConstants.COMBINE_SCRIPTS, false);
        configuration.add(SymbolConstants.COMPACT_JSON, false);
        configuration.add(SymbolConstants.COMPRESS_WHITESPACE, false);
        configuration.add(SymbolConstants.START_PAGE_NAME, "Index");
        configuration.add(SymbolConstants.FORM_CLIENT_LOGIC_ENABLED, false); //禁用客户端脚校验
        configuration.add(SymbolConstants.SUPPORTED_LOCALES, "zh_CN,en");//设置支持的语言
        configuration.add(SymbolConstants.CHARSET, "UTF-8");//设置配置文件和模板文件的编码

    }

    // make configuration from 'test.properties' on the classpath available as symbols
    public SymbolProvider buildClasspathResourceSymbolProvider() {
        return new EncodedClasspathResourceSymbolProvider("application.properties", Charsets.UTF_8);

    }

    public static void contributeSymbolSource(
            OrderedConfiguration<SymbolProvider> configuration,
            @InjectService("ClasspathResourceSymbolProvider") SymbolProvider provider) {
        configuration.add("ApplicationClasspathResource", provider,
                "after:SystemProperties", "before:ApplicationDefaults");

    }

    // 配置忽略某些路径不让Tapestry5处理，支持Servlet和自定义Filter
    public static void contributeIgnoredPathsFilter(Configuration<String> configuration,
                                                    final Logger logger,
                                                    @Symbol("tapestry.ignored-paths") String paths) {
        if (StringUtils.isNotEmpty(paths)) {
            for (String path : paths.split(",")) {
                logger.info("###add ignored path [{}] success!", path);
                configuration.add(path);
            }
        }

    }

}
