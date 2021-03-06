package org.drools.guvnor.client.explorer.navigation.qa;

import com.google.gwt.event.shared.EventBus;
import org.drools.guvnor.client.common.GenericCallback;
import org.drools.guvnor.client.explorer.AcceptItem;
import org.drools.guvnor.client.explorer.ClientFactory;
import org.drools.guvnor.client.messages.Constants;
import org.drools.guvnor.client.rpc.Module;
import org.drools.guvnor.client.util.Activity;

public class TestScenarioListActivity extends Activity {

    private final ClientFactory clientFactory;
    private final String moduleUuid;

    public TestScenarioListActivity(String moduleUuid,
                                    ClientFactory clientFactory) {
        this.moduleUuid = moduleUuid;
        this.clientFactory = clientFactory;
    }

    @Override
    public void start(AcceptItem tabbedPanel, EventBus eventBus) {
        openTestScenario( tabbedPanel );
    }

    public void openTestScenario(final AcceptItem tabbedPanel) {

        clientFactory.getModuleService().loadModule(
                moduleUuid,
                new GenericCallback<Module>() {
                    public void onSuccess(Module packageConfigData) {

                        tabbedPanel.add(
                                Constants.INSTANCE.ScenariosForPackage( packageConfigData.getName() ),
                                new ScenarioPackageScreen(
                                        packageConfigData.getUuid(),
                                        packageConfigData.getName(),
                                        clientFactory ) );
                    }
                } );

    }
}
