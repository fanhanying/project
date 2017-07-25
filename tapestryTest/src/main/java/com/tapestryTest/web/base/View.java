package com.tapestryTest.web.base;

import java.util.Map;

import org.apache.tapestry5.ClientBodyElement;
import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.annotations.Symbol;
import org.apache.tapestry5.services.Cookies;
import org.apache.tapestry5.services.PageRenderLinkSource;
import org.apache.tapestry5.services.RequestGlobals;
import org.apache.tapestry5.services.ajax.AjaxResponseRenderer;

import com.google.common.collect.Maps;

/** 
 * @author hanying.fan
 * @date 2017年7月24日 下午4:51:00 
 */
public abstract class View {

	private final static String SESSION_ATTRIBUTE_USER_PROFILE = "__user_profile__";

    private final Map<String, Object> caches = Maps.newConcurrentMap();

    @Inject
    protected RequestGlobals requestGlobals;

    @Inject
    protected Cookies cookies;

    @Inject
    protected AjaxResponseRenderer renderer;

    @Inject
    protected PageRenderLinkSource pageRenderLinkSource;

    @Inject
    @Symbol(SymbolConstants.PRODUCTION_MODE)
    @Property
    private boolean productionMode;

    @Inject
    @Symbol(SymbolConstants.APPLICATION_VERSION)
    @Property
    private String version;

    /**
     * 添加渲染的对象到队列中。
     *
     * @param zone 要渲染的对象，表示重刷该区域。
     * @return 返回异步刷新的渲染对象。支持fluid的调用方式。
     */
    public AjaxResponseRenderer addRender(ClientBodyElement zone) {
        return this.renderer.addRender(zone);
    }

    /**
     * 添加渲染的对象到队列中。
     *
     * @param clientId 要刷新的元素id
     * @param renderer 一个 {@link org.apache.tapestry5.Block},
     *                 {@link org.apache.tapestry5.runtime.Component} 的对象或者其他可以支持
     *                 {@linkplain org.apache.tapestry5.ioc.services.TypeCoercer coerced} 转化为
     *                 {@link org.apache.tapestry5.runtime.RenderCommand}类型的对象.
     * @return 返回异步刷新的渲染对象。支持fluid的调用方式。
     */
    public AjaxResponseRenderer addRender(String clientId, Object renderer) {
        return this.renderer.addRender(clientId, renderer);
    }

    /**
     * 获取当前的上下文相对路径。
     */
    public String getContext() {
        return this.requestGlobals.getRequest().getContextPath() + "/";
    }

    /**
     * 获取资静态文件的相对路径。
     */
    public String getAssets() {
        return this.requestGlobals.getRequest().getContextPath() + this.getAssetsVirtualPath();
    }

    /**
     * 获取资源文件的相对路径
     */
    public String getAssetsVirtualPath() {
        String assets = "/static";
        if (productionMode) {
            assets += "/" + this.version;
        }
        return assets;
    }

    /**
     * 获取当前请求的相对路径。
     */
    public String getPath() {
        return this.requestGlobals.getRequest().getPath();
    }

    /**
     * 获取当前物理路径。
     */
    public String getRealPath() {
        return this.requestGlobals.getHTTPServletRequest()
                .getSession()
                .getServletContext()
                .getRealPath("/");
    }

    /**
     * 获取指定相对路径绝对物理路劲。
     */
    public String getRealPath(String path) {
        return this.requestGlobals.getHTTPServletRequest()
                .getSession()
                .getServletContext()
                .getRealPath(path);
    }

    /**
     * 指示当前的请求是否是异步请求
     */
    public boolean isAjax() {
        return this.requestGlobals.getRequest().isXHR();
    }
}
