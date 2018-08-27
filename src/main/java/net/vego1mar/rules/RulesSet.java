package net.vego1mar.rules;

import java.util.ArrayList;
import java.util.List;

public class RulesSet {

    private List<Rule> rules;

    public RulesSet() {
        rules = new ArrayList<>();
    }

    public void push(Rule rule) {
        if (rule != null) {
            rules.add(rule);
        }
    }

    public void pop() {
        if (!rules.isEmpty()) {
            rules.remove(rules.size() - 1);
        }
    }

    public Rule get(int index) {
        if (index >= 0 && index < rules.size()) {
            return new Rule(rules.get(index));
        }

        return new Rule();
    }
}
