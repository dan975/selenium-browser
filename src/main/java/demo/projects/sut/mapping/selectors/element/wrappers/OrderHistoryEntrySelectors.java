package demo.projects.sut.mapping.selectors.element.wrappers;

import demo.projects.sut.mapping.selectors.BaseSelectors;
import lombok.Getter;
import org.openqa.selenium.By;

@Getter
public class OrderHistoryEntrySelectors extends BaseSelectors {
    protected By invoiceDownloadLink;
}
