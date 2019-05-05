package hr.fer.zemris.diplproj.heuristic.genprog.node.function;

import hr.fer.zemris.diplproj.heuristic.genprog.node.INode;
import hr.fer.zemris.diplproj.heuristic.genprog.node.terminal.ValueTerminalNode;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AndFunctionNodeTest {
    @Test
    void testAnd(){
        INode node = new AndFunctionNode();
        node.addChild(new ValueTerminalNode(0));
        node.addChild(new ValueTerminalNode(1));

        assertEquals(0, node.evaluate(List.of(0, 0)));
        assertEquals(0, node.evaluate(List.of(0, 1)));
        assertEquals(0, node.evaluate(List.of(1, 0)));
        assertEquals(1, node.evaluate(List.of(1, 1)));
    }

    @Test
    void testWrongChildCount(){
        INode node = new AndFunctionNode();
        node.addChild(new ValueTerminalNode(0));
        node.addChild(new ValueTerminalNode(1));

        assertThrows(IndexOutOfBoundsException.class, () -> node.addChild(new ValueTerminalNode(1)));
    }
}

