package de.codehat.levelbuilder;

import de.codehat.levelbuilder.model.TileType;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.fixture.JButtonFixture;
import org.assertj.swing.junit.testcase.AssertJSwingJUnitTestCase;
import org.junit.Test;

import static org.junit.Assert.*;

public class GuiTest extends AssertJSwingJUnitTestCase {

    private FrameFixture window;
    private Controller controller;

    public static String getTileButton(int row, int col) {
        return String.format("tileButton%d%d", row, col);
    }

    @Override
    protected void onSetUp() {
        controller = GuiActionRunner.execute(Controller::new);
        window = new FrameFixture(robot(), controller.getView());
        window.show();
    }

    @Test
    public void clickExportButtonWithoutGoal() {
        window.button("exportButton").click();
        window.optionPane().requireErrorMessage();
    }

    @Test
    public void clickExportButtonWithOneGoal() {
        window.comboBox("levelTypes").selectItem(TileType.GOAL_INDEX);
        window.button(getTileButton(0, 0)).click();
        window.button("exportButton").click();
        window.dialog().requireModal();
    }

    @Test
    public void clickTileWithTerrainOption() {
        JButtonFixture tileButton = window.button(getTileButton(0, 0));

        window.comboBox("levelTypes").selectItem(TileType.TERRAIN_INDEX);
        tileButton.click();

        assertEquals("t", tileButton.text());
    }

    @Test
    public void clickTileWithHedgeOption() {
        JButtonFixture tileButton = window.button(getTileButton(0, 0));

        window.comboBox("levelTypes").selectItem(TileType.HEDGE_INDEX);
        tileButton.click();

        assertEquals("h", tileButton.text());
    }

    @Test
    public void clickTileWithStartOption() {
        JButtonFixture tileButton = window.button(getTileButton(0, 0));

        window.comboBox("levelTypes").selectItem(TileType.START_INDEX);
        tileButton.click();

        assertEquals("s", tileButton.text());
    }

    @Test
    public void clickTileWithFoxOption() {
        JButtonFixture tileButton = window.button(getTileButton(0, 0));

        window.comboBox("levelTypes").selectItem(TileType.FOX_INDEX);
        tileButton.click();

        assertEquals("f", tileButton.text());
    }

    @Test
    public void clickSettingsButton() {
        window.button("settingsButton").click();
        window.optionPane().requireTitle("Level Size");
    }

    @Test
    public void changeLevelSize() {
        window.button("settingsButton").click();
        window.optionPane().spinner("rowsSpinner").enterText("6");
        window.optionPane().spinner("colsSpinner").enterText("6");
        window.optionPane().okButton().click();

        assertEquals(36, controller.getView().getTiles().size());
    }

}
