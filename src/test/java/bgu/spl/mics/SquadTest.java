package bgu.spl.mics;

import bgu.spl.mics.application.passiveObjects.Agent;
import bgu.spl.mics.application.passiveObjects.Squad;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SquadTest {
    Squad squad;

    @BeforeEach
    public void setUp() {
        squad = Squad.getInstance();
    }

    @Test
    public void testLoad() {
        Agent agent1 = new Agent();
        Agent agent2 = new Agent();
        Agent agent3 = new Agent();
        agent1.setSerialNumber("omri");
        agent2.setSerialNumber("yaakov");
        agent3.setSerialNumber("vais");
        Agent[] agents = {agent1, agent2, agent3};
        List<String> agentList = new ArrayList<>();
        for (Agent agent : agents) {
            agentList.add(agent.getName());
        }
        squad.load(agents);
        assertTrue(squad.getAgents(agentList));
    }

    @Test
    public void testReleaseAgents() {
        Agent agent1 = new Agent();
        Agent agent2 = new Agent();
        Agent agent3 = new Agent();
        agent1.setSerialNumber("omri");
        agent2.setSerialNumber("yaakov");
        agent3.setSerialNumber("vais");
        Agent[] agents = {agent1, agent2, agent3};
        squad.load(agents);
        List<String> agentList1 = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            agentList1.add(agents[i].getName());
        }
        List<String> agentList2 = new ArrayList<>();
        agentList2.add(agent3.getName());
        try {
            squad.sendAgents(agentList1, 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertFalse(squad.getAgents(agentList1));
        assertTrue(squad.getAgents(agentList2));
    }

}
