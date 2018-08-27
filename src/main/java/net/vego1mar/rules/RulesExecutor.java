package net.vego1mar.rules;

import net.vego1mar.rules.properties.AfterAction;
import net.vego1mar.rules.properties.From;
import net.vego1mar.rules.properties.In;
import net.vego1mar.rules.properties.SearchFor;
import net.vego1mar.rules.properties.SearchType;
import net.vego1mar.utils.WebPageDownloader;
import org.jetbrains.annotations.NotNull;

public class RulesExecutor {

    private Rule rule;
    private RulesSet rulesSet;
    private String webPageLink;
    private String webPageCode;
    private String content;
    private String htmlResult;
    private int activeRule;
    private int htmlCursor;

    public RulesExecutor(@NotNull Rule rule, @NotNull String webpage) {
        this.rule = rule;
        this.webPageLink = webpage;
        webPageCode = "";
        content = "";
        htmlResult = "";
        activeRule = 0;
        htmlCursor = 0;
    }

    public RulesExecutor(@NotNull RulesSet rulesSet, @NotNull String webpage) {
        // TODO: exception handling
        this(rulesSet.get(0), webpage);
        this.rulesSet = rulesSet;
    }

    public void prepare() {
        if (webPageCode.isEmpty()) {
            webPageCode = WebPageDownloader.getHtml(webPageLink);
        }
    }

    public void next() {
        switch (rule.in()) {
            case HTML:
                htmlResult = searchFor(rule.searchFor(), rule.searchType(), rule.searchText());
                break;
            case CONTENT:
            case COLLECTION:
                break;
        }

        switch (rule.useAs()) {
            case UPDATE_DATE:
            case LATEST_APP_VERSION:
            case WINDOWS_X64_PACKAGE_URL:
            case WINDOWS_X86_PACKAGE_URL:
                break;
        }

        if (rule.afterAction() == AfterAction.NEXT_RULE) {
            activeRule++;
            setActiveRule(activeRule);
        }
    }

    private void setActiveRule(int ruleIndex) {
        activeRule = ruleIndex;
        rule = rulesSet.get(ruleIndex);
    }

    @NotNull private String searchFor(@NotNull SearchFor searchForWhat, SearchType type, String text) {
        switch (searchForWhat) {
            case FIRST_OF:
                return searchFirstOf(type, text);
        }

        return "";
    }

    @NotNull private String searchFirstOf(SearchType type, String text) {
        int openingTag = webPageCode.indexOf(text, getFromIndex());

        if (type == SearchType.TAG) {
            text = "</" + text.substring(1);
            int closingTag = webPageCode.indexOf(text, getFromIndex());
            int closingTagPosition = closingTag + text.length();

            if (closingTagPosition >= webPageCode.length()) {
                content = webPageCode.substring(openingTag, webPageCode.length() - 1);
            } else {
                content = webPageCode.substring(openingTag, closingTagPosition);
            }
        }

        if (rule.in() != In.COLLECTION) {
            htmlCursor = openingTag;
        }

        return webPageCode.substring(openingTag);
    }

    private int getFromIndex() {
        if (rule.from() == From.CURSOR) {
            return htmlCursor;
        }

        return 0;
    }
}
