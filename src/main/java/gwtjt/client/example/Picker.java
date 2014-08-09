package gwtjt.client.example;

import gwtjt.client.GwtJt;

public class Picker extends GwtJt {
  public Picker() {
    _("section").attr("class", "picker").attr("ng-class", "{crummy: cmp.breadcrumbs.isNotEmpty}");
    __("header").attr("class", "ux-targeting-header");
    ___("section").attr("class", "filter");
    ____("input").attr("placeholder", "Type to filter items below").attr("type", "text");
    __("div").attr("class", "picker-contents scrollable-vertical");
    ___("div").attr("class", "path toolbar").attr("ng-if", "cmp.breadcrumbs.isNotEmpty");
    ____("button").attr("class", "ux-button icon directory-up").attr("ng-click", "cmp.breadcrumbsGoBack()");
    ____("ol").attr("class", "crumbs");
    _____("li").attr("ng-repeat", "crumb in cmp.breadcrumbs");
    ______("a").attr("href", "#").attr("ng-click", "cmp.showBreadcrumb($index)").innerText("{{cmp.nameFn(crumb.parentItem)}}");
    ___("ol").attr("id", "items");
    ____("li").attr("ng-mouseenter", "cmp.slideButton($event.target, true)").attr("ng-mouseleave", "cmp.slideButton($event.target, false)").attr("ng-repeat", "item in cmp.displayItems");
    _____("div").attr("class", "item geo country").attr("ng-class", "{parent: cmp.hasChildren(item)}");
    ______("span").attr("ng-click", "cmp.onClick(item)").innerText("{{cmp.nameFn(item)}}");
    _____("div").attr("class", "right");
    ______("span").attr("class", "ux-slidetoggle").attr("ng-if", "cmp.isNotMode");
    _______("button").attr("class", "ux-button exclude").attr("ng-click", "cmp.onExclude(item)").innerText("exclude");
    _______("button").attr("class", "ux-button active include").attr("ng-click", "cmp.onInclude(item)").innerText("include");
    ______("span").attr("ng-if", "cmp.isMode and !cmp.isMarked(item)");
    _______("button").attr("class", "ux-button active include").attr("ng-click", "cmp.onInclude(item)").attr("ng-if", "cmp.includeMode").innerText("include");
    _______("button").attr("class", "ux-button active exclude").attr("ng-click", "cmp.onExclude(item)").attr("ng-if", "cmp.excludeMode").innerText("exclude");
    ______("span").attr("ng-show", "cmp.isMarked(item)");
    _______("button").attr("class", "ux-button undoable included").attr("ng-click", "cmp.onRemove(item)").attr("ng-if", "cmp.isIncluded(item)").innerText("included");
    _______("button").attr("class", "ux-button undoable excluded").attr("ng-click", "cmp.onRemove(item)").attr("ng-if", "cmp.isExcluded(item)").innerText("excluded");

  }
}
