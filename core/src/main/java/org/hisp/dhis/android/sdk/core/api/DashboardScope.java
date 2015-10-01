/*
 * Copyright (c) 2015, University of Oslo
 *
 * All rights reserved.
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 * Neither the name of the HISP project nor the names of its contributors may
 * be used to endorse or promote products derived from this software without
 * specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.hisp.dhis.android.sdk.core.api;

import org.hisp.dhis.android.sdk.core.controllers.common.IDataController;
import org.hisp.dhis.android.sdk.core.network.APIException;
import org.hisp.dhis.android.sdk.models.dashboard.Dashboard;
import org.hisp.dhis.android.sdk.models.dashboard.DashboardItem;
import org.hisp.dhis.android.sdk.models.dashboard.DashboardItemContent;
import org.hisp.dhis.android.sdk.models.dashboard.IDashboardService;

import java.util.List;

public final class DashboardScope implements IDataController<Dashboard>, IDashboardService {
    private final IDataController<Dashboard> dataController;
    private final IDashboardService dashboardService;

    public DashboardScope(IDataController<Dashboard> dataController,
                          IDashboardService dashboardService) {
        this.dataController = dataController;
        this.dashboardService = dashboardService;
    }

    @Override
    public boolean add(Dashboard dashboard) {
        return dashboardService.add(dashboard);
    }

    @Override
    public boolean save(Dashboard object) {
        return dashboardService.save(object);
    }

    @Override
    public boolean update(Dashboard dashboard) {
        return dashboardService.update(dashboard);
    }

    @Override
    public boolean remove(Dashboard dashboard) {
        return dashboardService.remove(dashboard);
    }

    @Override
    public Dashboard get(long id) {
        return dashboardService.get(id);
    }

    @Override
    public List<Dashboard> list() {
        return dashboardService.list();
    }

    @Override
    public boolean addDashboardContent(Dashboard dashboard, DashboardItemContent content) {
        return dashboardService.addDashboardContent(dashboard, content);
    }

    @Override
    public DashboardItem getAvailableItemByType(Dashboard dashboard, String type) {
        return dashboardService.getAvailableItemByType(dashboard, type);
    }

    @Override
    public void sync() throws APIException {
        dataController.sync();
    }
}
