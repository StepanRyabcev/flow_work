import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Expression {
	private String expression;
    private List<String> variablesNames;
    private List<Integer> variablesValues;
    
    public Expression() {}

    public String getExpression() {
        return expression;
    }

    @JsonProperty("expression")
    public void setExpression(String expression) {
        this.expression = expression;
    }

    public List<String> getVariablesNames() {
        return variablesNames;
    }

    @JsonProperty("variablesNames")
    public void setVariablesNames(List<String> variablesNames) {
        this.variablesNames = variablesNames;
    }

    public List<Integer> getVariablesValues() {
        return variablesValues;
    }

    @JsonProperty("variablesValues")
    public void setVariablesValues(List<Integer> variablesValues) {
        this.variablesValues = variablesValues;
    }
}

