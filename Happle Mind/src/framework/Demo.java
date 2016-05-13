package framework;

class Demo{
    public int ScenarioNo;
    public String ScenarioID;
    public String ScenarioDesc;
    boolean TriggerExecution;    

    public Demo(int ScenarioNo,String ScenarioID, String ScenarioDesc, boolean TriggerExecution){
        this.ScenarioNo=ScenarioNo;
        this.ScenarioID = ScenarioID;
        this.ScenarioDesc = ScenarioDesc;
        this.TriggerExecution = TriggerExecution;        
    }
    public int getScenarioNo() {
        return ScenarioNo;
    }
    public String getScenarioID() {
        return ScenarioID;
    }
    public String getScenarioDesc() {
        return ScenarioDesc;
    }
    public boolean getTriggerExecution() {
        return TriggerExecution;
    }    
    @Override
    public String toString() {
        return "Test Scenario Details [Scenario No=" + ScenarioNo + ", Scenario ID=" + ScenarioID
                + ", Scenario Description=" + ScenarioDesc + ", Trigger Execution=" + TriggerExecution + "]";
    }
}
