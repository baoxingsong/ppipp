package Action;

import static org.junit.Assert.*;

import me.songbx.action.BinaAction;

import org.junit.Test;

public class BinaActionTest {

	@Test
	public void testStartBina() {
		BinaAction binaAction=new BinaAction();
		binaAction.setCondition(1);
		binaAction.setExpectationValue("asdfasdf");
		binaAction.startBina();
	}

	@Test
	public void testResultBina() {
		BinaAction binaAction=new BinaAction();
		binaAction.setUuid("58a9869d-9ed7-49fa-a4d9-659c52d00060");
		binaAction.resultBina();
	}

}
