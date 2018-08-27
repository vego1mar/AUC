package net.vego1mar.rules;

import net.vego1mar.rules.properties.AfterAction;
import net.vego1mar.rules.properties.From;
import net.vego1mar.rules.properties.In;
import net.vego1mar.rules.properties.SearchFor;
import net.vego1mar.rules.properties.SearchType;
import net.vego1mar.rules.properties.UseAs;
import org.jetbrains.annotations.NotNull;

public class Rule {

    From from;
    In in;
    SearchFor searchFor;
    SearchType searchType;
    String searchText;
    UseAs useAs;
    AfterAction afterAction;

    public Rule() {
        // TODO: consider using a builder design pattern
        from = From.CURSOR;
        in = In.HTML;
        searchFor = SearchFor.FIRST_OF;
        searchType = SearchType.TAG;
        searchText = "";
        useAs = UseAs.IGNORE;
        afterAction = AfterAction.STOP;
    }

    public Rule(@NotNull Rule otherRule) {
        from(otherRule.from());
        in(otherRule.in());
        searchFor(otherRule.searchFor());
        searchType(otherRule.searchType());
        searchText(otherRule.searchText());
        useAs(otherRule.useAs());
        afterAction(otherRule.afterAction());
    }

    public From from() {
        return from;
    }

    public void from(From from) {
        this.from = from;
    }

    public In in() {
        return in;
    }

    public void in(In in) {
        this.in = in;
    }

    public SearchFor searchFor() {
        return searchFor;
    }

    public void searchFor(SearchFor searchFor) {
        this.searchFor = searchFor;
    }

    public SearchType searchType() {
        return searchType;
    }

    public void searchType(SearchType searchType) {
        this.searchType = searchType;
    }

    public String searchText() {
        return searchText;
    }

    public void searchText(String searchText) {
        this.searchText = searchText;
    }

    public UseAs useAs() {
        return useAs;
    }

    public void useAs(UseAs useAs) {
        this.useAs = useAs;
    }

    public AfterAction afterAction() {
        return afterAction;
    }

    public void afterAction(AfterAction afterAction) {
        this.afterAction = afterAction;
    }
}
