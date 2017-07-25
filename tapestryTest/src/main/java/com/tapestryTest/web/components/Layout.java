package com.tapestryTest.web.components;

import org.apache.tapestry5.Block;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.springframework.core.env.Environment;

import com.tapestryTest.web.base.View;

/** 
 * @author hanying.fan
 * @date 2017年7月24日 下午5:01:30 
 */
public class Layout extends View {

	@Parameter
    private String title;

    @Parameter
    private Block styles;

    @Inject
    protected ComponentResources resources;

    @Inject
    private Environment environment;

    void setupRender() {
    }

    public String defaultTitle() {
        return this.resources.getPageName();
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the styles
     */
    public Block getStyles() {
        return styles;
    }

    /**
     * @param styles the styles to set
     */
    public void setStyles(Block styles) {
        this.styles = styles;
    }
}
