package hr.fer.zemris.diplproj.heuristic.genprog.node.function;

import hr.fer.zemris.diplproj.heuristic.genprog.node.INode;
import hr.fer.zemris.diplproj.heuristic.genprog.node.terminal.ValueTerminalNode;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BiconditionFunctionNodeTest {
    @Test
    void testBicondition(){
        INode node = new BiconditionFunctionNode();
        node.addChild(new ValueTerminalNode(0));
        node.addChild(new ValueTerminalNode(1));

        assertEquals(1, node.evaluate(List.of(0, 0)));
        assertEquals(0, node.evaluate(List.of(0, 1)));
        assertEquals(0, node.evaluate(List.of(1, 0)));
        assertEquals(1, node.evaluate(List.of(1, 1)));
    }
}

