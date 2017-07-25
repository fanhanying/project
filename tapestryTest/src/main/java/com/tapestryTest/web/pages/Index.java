package com.tapestryTest.web.pages;

import java.util.List;

import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.ioc.annotations.Inject;

import com.tapestryTest.domain.User;
import com.tapestryTest.service.UserService;
import com.tapestryTest.web.base.View;

/**
 * @author Inspireso Team
 */
public class Index extends View {

    @InjectComponent
    private Zone mainZone;

    @Inject
    private UserService userService;

    @Property
    private String code;

    @Property
    private String name;

    @Property
    private User current;

    void setupRender() {

    }

    void onSuccessFromUserForm() {
        User user = new User();
        user.audit(null);
        user.setCode(code);
        user.setName(name);
        userService.save(user);
        this.addRender(mainZone);
    }

    public List<User> getUserList() {
        return userService.findAll();
    }


}
