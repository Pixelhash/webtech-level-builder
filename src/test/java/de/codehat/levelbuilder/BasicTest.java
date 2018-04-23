package de.codehat.levelbuilder;

import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.junit.testcase.AssertJSwingJUnitTestCase;
import org.junit.Test;

public class BasicTest extends AssertJSwingJUnitTestCase {

    private FrameFixture window;

    @Override
    protected void onSetUp() {
        Controller controller = GuiActionRunner.execute(Controller::new);
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
        window.comboBox("levelTypes").selectItem(3);
        window.button("tileButton00").click();
        window.button("exportButton").click();
        window.dialog().requireModal();
    }

    @Test
    public void clickSettingsButton() {
        window.button("settingsButton").click();
        window.optionPane().requireTitle("Level Size");
    }

}
