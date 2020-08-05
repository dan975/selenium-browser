package demo.projects.sut.mapping.selectors.pages.base.components;

import demo.projects.sut.mapping.selectors.BaseSelectors;
import lombok.Getter;
import org.openqa.selenium.By;

import javax.annotation.PostConstruct;

@Getter
public abstract class HeaderComponentSelectors extends BaseSelectors {
    protected By signInBtn;
    protected By signOutBtn;
    protected By signedInUserName;
    protected By logo;
    protected By searchBox;
    protected By searchBtn;
    protected By productTab;

    @PostConstruct
    public void init() {
        this.logo = getByFactory().getById("header_logo");
    }
}
