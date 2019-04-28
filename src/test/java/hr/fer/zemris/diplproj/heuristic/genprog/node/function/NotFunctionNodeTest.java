package hr.fer.zemris.diplproj.heuristic.genprog.node.function;

import hr.fer.zemris.diplproj.heuristic.genprog.node.INode;
import hr.fer.zemris.diplproj.heuristic.genprog.node.terminal.ValueTerminalNode;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NotFunctionNodeTest {
    @Test
    public void testNot(){
        INode node = new NotFunctionNode();
        node.addChild(new ValueTerminalNode(0));

        assertEquals(0, node.evaluate(List.of(1)));
        assertEquals(1, node.evaluate(List.of(0)));
    }
}

