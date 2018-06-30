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
        window.menuItem("saveFileItem").click();
        window.optionPane().requireErrorMessage();
    }

    @Test
    public void clickExportButtonWithOneGoal() {
        window.comboBox("tileTypes").selectItem(TileType.GOAL_INDEX);
        window.button(getTileButton(0, 0)).click();
        window.menuItem("saveFileItem").click();
        window.dialog().requireModal();
    }

    @Test
    public void clickTileWithTerrainOption() {
        JButtonFixture tileButton = window.button(getTileButton(0, 0));

        window.comboBox("tileTypes").selectItem(TileType.TERRAIN_INDEX);
        tileButton.click();

        assertEquals("t", tileButton.text());
    }

    @Test
    public void clickTileWithHedgeOption() {
        JButtonFixture tileButton = window.button(getTileButton(0, 0));

        window.comboBox("tileTypes").selectItem(TileType.HEDGE_INDEX);
        tileButton.click();

        assertEquals("h", tileButton.text());
    }

    @Test
    public void clickTileWithStartOption() {
        JButtonFixture tileButton = window.button(getTileButton(0, 0));

        window.comboBox("tileTypes").selectItem(TileType.RABBIT_INDEX);
        tileButton.click();

        assertEquals("r", tileButton.text());
    }

    @Test
    public void clickTileWithFoxOption() {
        JButtonFixture tileButton = window.button(getTileButton(0, 0));

        window.comboBox("tileTypes").selectItem(TileType.FOX_INDEX);
        tileButton.click();

        window.optionPane().okButton().click();

        assertEquals("f", tileButton.text());
    }

    @Test
    public void clickTileWithSpeedPowerUpOption() {
        JButtonFixture tileButton = window.button(getTileButton(0, 0));

        window.comboBox("tileTypes").selectItem(TileType.SPEED_POWERUP_INDEX);
        tileButton.click();

        window.optionPane().okButton().click();

        assertEquals("sp", tileButton.text());
    }

    @Test
    public void clickSettingsButton() {
        window.button("settingsButton").click();
        window.optionPane().requireTitle("Level Size");
    }

    @Test
    public void clickAboutButton() {
        window.menuItem("aboutItem").click();
        window.optionPane().requireTitle("About");
    }

    @Test
    public void clickNewLevelButton() {
        window.menuItem("newFileItem").click();
        window.optionPane().requireTitle("New Level");
    }

    @Test
    public void clickLoadLevelButton() {
        window.menuItem("loadFileItem").click();

        window.fileChooser().requireVisible();
    }

    @Test
    public void clickSaveLevelButton() {
        window.menuItem("saveFileItem").click();

        // Check for "Error" title here, because not all required tiles are clicked (rabbit and goal).
        window.optionPane().requireTitle("Error");
    }

    @Test
    public void changeLevelSize() {
        window.button("settingsButton").click();
        window.optionPane().spinner("rowsSpinner").enterText("6");
        window.optionPane().spinner("colsSpinner").enterText("7");
        window.optionPane().okButton().click();

        assertEquals(42, controller.getModel().getTiles().length * controller.getModel().getTiles()[0].length);
    }
}