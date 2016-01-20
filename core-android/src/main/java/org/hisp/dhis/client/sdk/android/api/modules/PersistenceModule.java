/*
 * Copyright (c) 2016, University of Oslo
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

package org.hisp.dhis.client.sdk.android.api.modules;

import android.content.Context;

import com.raizlabs.android.dbflow.config.FlowManager;

import org.hisp.dhis.client.sdk.android.common.FailedItemStore;
import org.hisp.dhis.client.sdk.android.common.ModelStore;
import org.hisp.dhis.client.sdk.android.common.base.IMapper;
import org.hisp.dhis.client.sdk.android.common.state.IStateMapper;
import org.hisp.dhis.client.sdk.android.common.state.StateMapper;
import org.hisp.dhis.client.sdk.android.common.state.StateStore;
import org.hisp.dhis.client.sdk.android.constant.ConstantMapper;
import org.hisp.dhis.client.sdk.android.constant.ConstantStore;
import org.hisp.dhis.client.sdk.android.dashboard.DashboardContentMapper;
import org.hisp.dhis.client.sdk.android.dashboard.DashboardContentStore;
import org.hisp.dhis.client.sdk.android.dashboard.DashboardElementMapper;
import org.hisp.dhis.client.sdk.android.dashboard.DashboardElementStore;
import org.hisp.dhis.client.sdk.android.dashboard.DashboardItemMapper;
import org.hisp.dhis.client.sdk.android.dashboard.DashboardItemStore;
import org.hisp.dhis.client.sdk.android.dashboard.DashboardMapper;
import org.hisp.dhis.client.sdk.android.dashboard.DashboardStore;
import org.hisp.dhis.client.sdk.android.dataelement.DataElementMapper;
import org.hisp.dhis.client.sdk.android.dataelement.DataElementStore;
import org.hisp.dhis.client.sdk.android.enrollment.EnrollmentMapper;
import org.hisp.dhis.client.sdk.android.enrollment.EnrollmentStore;
import org.hisp.dhis.client.sdk.android.event.EventMapper;
import org.hisp.dhis.client.sdk.android.event.EventStore;
import org.hisp.dhis.client.sdk.android.flow.Constant$Flow;
import org.hisp.dhis.client.sdk.android.flow.Dashboard$Flow;
import org.hisp.dhis.client.sdk.android.flow.DashboardContent$Flow;
import org.hisp.dhis.client.sdk.android.flow.DashboardElement$Flow;
import org.hisp.dhis.client.sdk.android.flow.DashboardItem$Flow;
import org.hisp.dhis.client.sdk.android.flow.DataElement$Flow;
import org.hisp.dhis.client.sdk.android.flow.Enrollment$Flow;
import org.hisp.dhis.client.sdk.android.flow.Event$Flow;
import org.hisp.dhis.client.sdk.android.flow.Option$Flow;
import org.hisp.dhis.client.sdk.android.flow.OptionSet$Flow;
import org.hisp.dhis.client.sdk.android.flow.OrganisationUnit$Flow;
import org.hisp.dhis.client.sdk.android.flow.Program$Flow;
import org.hisp.dhis.client.sdk.android.flow.ProgramIndicator$Flow;
import org.hisp.dhis.client.sdk.android.flow.ProgramRule$Flow;
import org.hisp.dhis.client.sdk.android.flow.ProgramRuleAction$Flow;
import org.hisp.dhis.client.sdk.android.flow.ProgramRuleVariable$Flow;
import org.hisp.dhis.client.sdk.android.flow.ProgramStage$Flow;
import org.hisp.dhis.client.sdk.android.flow.ProgramStageDataElement$Flow;
import org.hisp.dhis.client.sdk.android.flow.ProgramStageSection$Flow;
import org.hisp.dhis.client.sdk.android.flow.ProgramTrackedEntityAttribute$Flow;
import org.hisp.dhis.client.sdk.android.flow.Relationship$Flow;
import org.hisp.dhis.client.sdk.android.flow.RelationshipType$Flow;
import org.hisp.dhis.client.sdk.android.flow.TrackedEntity$Flow;
import org.hisp.dhis.client.sdk.android.flow.TrackedEntityAttribute$Flow;
import org.hisp.dhis.client.sdk.android.flow.TrackedEntityAttributeValue$Flow;
import org.hisp.dhis.client.sdk.android.flow.TrackedEntityDataValue$Flow;
import org.hisp.dhis.client.sdk.android.flow.TrackedEntityInstance$Flow;
import org.hisp.dhis.client.sdk.android.flow.User$Flow;
import org.hisp.dhis.client.sdk.android.flow.UserAccount$Flow;
import org.hisp.dhis.client.sdk.android.interpretation.InterpretationCommentStore;
import org.hisp.dhis.client.sdk.android.interpretation.InterpretationElementStore;
import org.hisp.dhis.client.sdk.android.interpretation.InterpretationStore;
import org.hisp.dhis.client.sdk.android.optionset.OptionMapper;
import org.hisp.dhis.client.sdk.android.optionset.OptionSetMapper;
import org.hisp.dhis.client.sdk.android.optionset.OptionSetStore;
import org.hisp.dhis.client.sdk.android.optionset.OptionStore;
import org.hisp.dhis.client.sdk.android.organisationunit.OrganisationUnitMapper;
import org.hisp.dhis.client.sdk.android.organisationunit.OrganisationUnitStore;
import org.hisp.dhis.client.sdk.android.program.ProgramIndicatorMapper;
import org.hisp.dhis.client.sdk.android.program.ProgramIndicatorStore;
import org.hisp.dhis.client.sdk.android.program.ProgramMapper;
import org.hisp.dhis.client.sdk.android.program.ProgramRuleActionMapper;
import org.hisp.dhis.client.sdk.android.program.ProgramRuleActionStore;
import org.hisp.dhis.client.sdk.android.program.ProgramRuleMapper;
import org.hisp.dhis.client.sdk.android.program.ProgramRuleStore;
import org.hisp.dhis.client.sdk.android.program.ProgramRuleVariableMapper;
import org.hisp.dhis.client.sdk.android.program.ProgramRuleVariableStore;
import org.hisp.dhis.client.sdk.android.program.ProgramStageDataElementMapper;
import org.hisp.dhis.client.sdk.android.program.ProgramStageDataElementStore;
import org.hisp.dhis.client.sdk.android.program.ProgramStageMapper;
import org.hisp.dhis.client.sdk.android.program.ProgramStageSectionMapper;
import org.hisp.dhis.client.sdk.android.program.ProgramStageSectionStore;
import org.hisp.dhis.client.sdk.android.program.ProgramStageStore;
import org.hisp.dhis.client.sdk.android.program.ProgramStore;
import org.hisp.dhis.client.sdk.android.program.ProgramTrackedEntityAttributeMapper;
import org.hisp.dhis.client.sdk.android.program.ProgramTrackedEntityAttributeStore;
import org.hisp.dhis.client.sdk.android.relationship.RelationshipStore;
import org.hisp.dhis.client.sdk.android.relationship.RelationshipTypeMapper;
import org.hisp.dhis.client.sdk.android.relationship.RelationshipTypeStore;
import org.hisp.dhis.client.sdk.android.trackedentity.TrackedEntityAttributeMapper;
import org.hisp.dhis.client.sdk.android.trackedentity.TrackedEntityAttributeStore;
import org.hisp.dhis.client.sdk.android.trackedentity.TrackedEntityAttributeValueStore;
import org.hisp.dhis.client.sdk.android.trackedentity.TrackedEntityDataValueMapper;
import org.hisp.dhis.client.sdk.android.trackedentity.TrackedEntityDataValueStore;
import org.hisp.dhis.client.sdk.android.trackedentity.TrackedEntityInstanceMapper;
import org.hisp.dhis.client.sdk.android.trackedentity.TrackedEntityInstanceStore;
import org.hisp.dhis.client.sdk.android.trackedentity.TrackedEntityMapper;
import org.hisp.dhis.client.sdk.android.trackedentity.TrackedEntityStore;
import org.hisp.dhis.client.sdk.android.user.UserAccountMapper;
import org.hisp.dhis.client.sdk.android.user.UserAccountStore;
import org.hisp.dhis.client.sdk.android.user.UserMapper;
import org.hisp.dhis.client.sdk.android.user.UserStore;
import org.hisp.dhis.client.sdk.core.common.IFailedItemStore;
import org.hisp.dhis.client.sdk.core.common.IModelsStore;
import org.hisp.dhis.client.sdk.core.common.IStateStore;
import org.hisp.dhis.client.sdk.core.common.persistence.IIdentifiableObjectStore;
import org.hisp.dhis.client.sdk.core.common.persistence.IPersistenceModule;
import org.hisp.dhis.client.sdk.core.common.persistence.ITransactionManager;
import org.hisp.dhis.client.sdk.core.constant.IConstantStore;
import org.hisp.dhis.client.sdk.core.dashboard.IDashboardElementStore;
import org.hisp.dhis.client.sdk.core.dashboard.IDashboardItemContentStore;
import org.hisp.dhis.client.sdk.core.dashboard.IDashboardItemStore;
import org.hisp.dhis.client.sdk.core.dashboard.IDashboardStore;
import org.hisp.dhis.client.sdk.core.dataelement.IDataElementStore;
import org.hisp.dhis.client.sdk.core.dataset.IDataSetStore;
import org.hisp.dhis.client.sdk.core.enrollment.IEnrollmentStore;
import org.hisp.dhis.client.sdk.core.event.IEventStore;
import org.hisp.dhis.client.sdk.core.interpretation.IInterpretationCommentStore;
import org.hisp.dhis.client.sdk.core.interpretation.IInterpretationElementStore;
import org.hisp.dhis.client.sdk.core.optionset.IOptionSetStore;
import org.hisp.dhis.client.sdk.core.optionset.IOptionStore;
import org.hisp.dhis.client.sdk.core.organisationunit.IOrganisationUnitStore;
import org.hisp.dhis.client.sdk.core.program.IProgramIndicatorStore;
import org.hisp.dhis.client.sdk.core.program.IProgramRuleActionStore;
import org.hisp.dhis.client.sdk.core.program.IProgramRuleStore;
import org.hisp.dhis.client.sdk.core.program.IProgramRuleVariableStore;
import org.hisp.dhis.client.sdk.core.program.IProgramStageDataElementStore;
import org.hisp.dhis.client.sdk.core.program.IProgramStageSectionStore;
import org.hisp.dhis.client.sdk.core.program.IProgramStageStore;
import org.hisp.dhis.client.sdk.core.program.IProgramStore;
import org.hisp.dhis.client.sdk.core.program.IProgramTrackedEntityAttributeStore;
import org.hisp.dhis.client.sdk.core.relationship.IRelationshipStore;
import org.hisp.dhis.client.sdk.core.trackedentity.ITrackedEntityAttributeStore;
import org.hisp.dhis.client.sdk.core.trackedentity.ITrackedEntityAttributeValueStore;
import org.hisp.dhis.client.sdk.core.trackedentity.ITrackedEntityDataValueStore;
import org.hisp.dhis.client.sdk.core.trackedentity.ITrackedEntityInstanceStore;
import org.hisp.dhis.client.sdk.core.user.IUserAccountStore;
import org.hisp.dhis.client.sdk.core.user.IUserStore;
import org.hisp.dhis.client.sdk.models.constant.Constant;
import org.hisp.dhis.client.sdk.models.dashboard.Dashboard;
import org.hisp.dhis.client.sdk.models.dashboard.DashboardContent;
import org.hisp.dhis.client.sdk.models.dashboard.DashboardElement;
import org.hisp.dhis.client.sdk.models.dashboard.DashboardItem;
import org.hisp.dhis.client.sdk.models.dataelement.DataElement;
import org.hisp.dhis.client.sdk.models.enrollment.Enrollment;
import org.hisp.dhis.client.sdk.models.event.Event;
import org.hisp.dhis.client.sdk.models.interpretation.Interpretation;
import org.hisp.dhis.client.sdk.models.optionset.Option;
import org.hisp.dhis.client.sdk.models.optionset.OptionSet;
import org.hisp.dhis.client.sdk.models.organisationunit.OrganisationUnit;
import org.hisp.dhis.client.sdk.models.program.Program;
import org.hisp.dhis.client.sdk.models.program.ProgramIndicator;
import org.hisp.dhis.client.sdk.models.program.ProgramRule;
import org.hisp.dhis.client.sdk.models.program.ProgramRuleAction;
import org.hisp.dhis.client.sdk.models.program.ProgramRuleVariable;
import org.hisp.dhis.client.sdk.models.program.ProgramStage;
import org.hisp.dhis.client.sdk.models.program.ProgramStageDataElement;
import org.hisp.dhis.client.sdk.models.program.ProgramStageSection;
import org.hisp.dhis.client.sdk.models.program.ProgramTrackedEntityAttribute;
import org.hisp.dhis.client.sdk.models.relationship.Relationship;
import org.hisp.dhis.client.sdk.models.relationship.RelationshipType;
import org.hisp.dhis.client.sdk.models.trackedentity.TrackedEntity;
import org.hisp.dhis.client.sdk.models.trackedentity.TrackedEntityAttribute;
import org.hisp.dhis.client.sdk.models.trackedentity.TrackedEntityAttributeValue;
import org.hisp.dhis.client.sdk.models.trackedentity.TrackedEntityDataValue;
import org.hisp.dhis.client.sdk.models.trackedentity.TrackedEntityInstance;
import org.hisp.dhis.client.sdk.models.user.User;
import org.hisp.dhis.client.sdk.models.user.UserAccount;
import org.hisp.dhis.client.sdk.models.utils.IModelUtils;
import org.hisp.dhis.client.sdk.models.utils.ModelUtils;

public class PersistenceModule implements IPersistenceModule {

    // Utility classes.
    private final ITransactionManager transactionManager;
    private final IModelUtils modelUtils;

    // UserAccount related dependencies.
    private final IUserAccountStore userAccountStore;


    private final IStateStore stateStore;
    private final IDashboardStore dashboardStore;
    private final IDashboardItemStore dashboardItemStore;
    private final IDashboardElementStore dashboardElementStore;
    private final IDashboardItemContentStore dashboardItemContentStore;

    // Meta data store objects
    private final IIdentifiableObjectStore<Constant> constantStore;
    private final IIdentifiableObjectStore<DataElement> dataElementStore;
    private final IOptionStore optionStore;
    private final IIdentifiableObjectStore<OptionSet> optionSetStore;
    private final IOrganisationUnitStore organisationUnitStore;
    private final IProgramStore programStore;
    private final IIdentifiableObjectStore<TrackedEntity> trackedEntityStore;
    private final IIdentifiableObjectStore<TrackedEntityAttribute> trackedEntityAttributeStore;
    private final IProgramTrackedEntityAttributeStore programTrackedEntityAttributeStore;
    private final IProgramStageDataElementStore programStageDataElementStore;
    private final IProgramIndicatorStore programIndicatorStore;
    private final IProgramStageSectionStore programStageSectionStore;
    private final IProgramStageStore programStageStore;
    private final IProgramRuleStore programRuleStore;
    private final IProgramRuleActionStore programRuleActionStore;
    private final IProgramRuleVariableStore programRuleVariableStore;
    private final IIdentifiableObjectStore<RelationshipType> relationshipTypeStore;

//    private final IDataSetStore dataSetStore;

    //Tracker store objects
    private final ITrackedEntityAttributeValueStore trackedEntityAttributeValueStore;
    private final IRelationshipStore relationshipStore;
    private final ITrackedEntityInstanceStore trackedEntityInstanceStore;
    private final ITrackedEntityDataValueStore trackedEntityDataValueStore;
    private final IEventStore eventStore;
    private final IEnrollmentStore enrollmentStore;

    // Interpretation store objects
    private final IIdentifiableObjectStore<Interpretation> interpretationStore;
    private final IInterpretationCommentStore interpretationCommentStore;
    private final IInterpretationElementStore interpretationElementStore;

    // User store object
    private final IUserStore userStore;

    private final IFailedItemStore failedItemStore;

    private final IModelsStore modelsStore;

    public PersistenceModule(Context context) {
        FlowManager.init(context);

        modelUtils = new ModelUtils();
        transactionManager = new TransactionManager(modelUtils);


        userAccountStore = new UserAccountStore(MapperModule.getInstance().getUserAccountMapper());

        stateStore = new StateStore(MapperModule.getInstance().getStateMapper(),
                MapperModule.getInstance().getDashboardMapper(),
                MapperModule.getInstance().getDashboardItemMapper(),
                MapperModule.getInstance().getDashboardElementMapper(),
                MapperModule.getInstance().getEventMapper(),
                MapperModule.getInstance().getEnrollmentMapper(),
                MapperModule.getInstance().getTrackedEntityInstanceMapper());
        dashboardStore = new DashboardStore(MapperModule.getInstance().getDashboardMapper());
        dashboardItemStore = new DashboardItemStore(MapperModule.getInstance().getDashboardItemMapper());
        dashboardElementStore = new DashboardElementStore(MapperModule.getInstance().getDashboardElementMapper());
        dashboardItemContentStore = new DashboardContentStore(MapperModule.getInstance().getDashboardContentMapper());
        constantStore = new ConstantStore(MapperModule.getInstance().getConstantMapper());
        dataElementStore = new DataElementStore(MapperModule.getInstance().getDataElementMapper());
        optionStore = new OptionStore(MapperModule.getInstance().getOptionMapper());
        optionSetStore = new OptionSetStore(MapperModule.getInstance().getOptionSetMapper(), optionStore);
        organisationUnitStore = new OrganisationUnitStore(MapperModule.getInstance().getOrganisationUnitMapper());
        programStore = new ProgramStore(MapperModule.getInstance().getProgramMapper(), transactionManager, MapperModule.getInstance().getOrganisationUnitMapper());
        trackedEntityStore = new TrackedEntityStore(MapperModule.getInstance().getTrackedEntityMapper());
        trackedEntityAttributeStore = new TrackedEntityAttributeStore(MapperModule.getInstance().getTrackedEntityAttributeMapper());
        programTrackedEntityAttributeStore = new ProgramTrackedEntityAttributeStore(MapperModule.getInstance().getProgramTrackedEntityAttributeMapper());
        programStageDataElementStore = new ProgramStageDataElementStore(MapperModule.getInstance().getProgramStageDataElementMapper());
        programIndicatorStore = new ProgramIndicatorStore(MapperModule.getInstance().getProgramIndicatorMapper());
        programStageSectionStore = new ProgramStageSectionStore(MapperModule.getInstance().getProgramStageSectionMapper());
        programStageStore = new ProgramStageStore(MapperModule.getInstance().getProgramStageMapper());
        programRuleActionStore = new ProgramRuleActionStore(MapperModule.getInstance().getProgramRuleActionMapper());
        programRuleStore = new ProgramRuleStore(MapperModule.getInstance().getProgramRuleMapper(), programRuleActionStore, MapperModule.getInstance().getProgramRuleActionMapper());
        programRuleVariableStore = new ProgramRuleVariableStore(MapperModule.getInstance().getProgramRuleVariableMapper());
        relationshipTypeStore = new RelationshipTypeStore(MapperModule.getInstance().getRelationshipTypeMapper());
//        dataSetStore = new DataSetStore(dataSetMapper, organisationUnitMapper);
        trackedEntityAttributeValueStore = new TrackedEntityAttributeValueStore(MapperModule.getInstance().getTrackedEntityAttributeValueMapper(), stateStore, programStore);
        relationshipStore = new RelationshipStore(MapperModule.getInstance().getRelationshipMapper(), stateStore);
        trackedEntityInstanceStore = new TrackedEntityInstanceStore(MapperModule.getInstance().getTrackedEntityInstanceMapper(), stateStore);
        trackedEntityDataValueStore = new TrackedEntityDataValueStore(MapperModule.getInstance().getTrackedEntityDataValueMapper(), stateStore);
        eventStore = new EventStore(MapperModule.getInstance().getEventMapper(), stateStore);
        enrollmentStore = new EnrollmentStore(eventStore, stateStore, trackedEntityAttributeValueStore, MapperModule.getInstance().getEnrollmentMapper());
        interpretationStore = new InterpretationStore();
        interpretationCommentStore = new InterpretationCommentStore();
        interpretationElementStore = new InterpretationElementStore();
        failedItemStore = new FailedItemStore();
        userStore = new UserStore(MapperModule.getInstance().getUserMapper());

        modelsStore = new ModelStore();
    }

    @Override
    public ITransactionManager getTransactionManager() {
        return transactionManager;
    }

    @Override
    public IUserAccountStore getUserAccountStore() {
        return userAccountStore;
    }


    @Override
    public IStateStore getStateStore() {
//        return stateStore;
        return null;
    }

    @Override
    public IDashboardStore getDashboardStore() {
//        return dashboardStore;
        return null;
    }

    @Override
    public IDashboardItemStore getDashboardItemStore() {
//        return dashboardItemStore;
        return null;
    }

    @Override
    public IDashboardElementStore getDashboardElementStore() {
//        return dashboardElementStore;
        return null;
    }

    @Override
    public IDashboardItemContentStore getDashboardContentStore() {
//        return dashboardItemContentStore;
        return null;
    }

    @Override
    public IConstantStore getConstantStore() {
//        return constantStore;
        return null;
    }

    @Override
    public IDataElementStore getDataElementStore() {
//        return dataElementStore;
        return null;
    }

    @Override
    public IOptionStore getOptionStore() {
//        return optionStore;
        return null;
    }

    @Override
    public IOptionSetStore getOptionSetStore() {
//        return optionSetStore;
        return null;
    }

    @Override
    public IOrganisationUnitStore getOrganisationUnitStore() {
//        return organisationUnitStore;
        return null;
    }

    @Override
    public IProgramStore getProgramStore() {
//        return programStore;
        return null;
    }

    @Override
    public IIdentifiableObjectStore<TrackedEntity> getTrackedEntityStore() {
//        return trackedEntityStore;
        return null;
    }

    @Override
    public ITrackedEntityAttributeStore getTrackedEntityAttributeStore() {
//        return trackedEntityAttributeStore;
        return null;
    }

    @Override
    public IProgramTrackedEntityAttributeStore getProgramTrackedEntityAttributeStore() {
//        return programTrackedEntityAttributeStore;
        return null;
    }

    @Override
    public IProgramStageDataElementStore getProgramStageDataElementStore() {
//        return programStageDataElementStore;
        return null;
    }

    @Override
    public IProgramIndicatorStore getProgramIndicatorStore() {
//        return programIndicatorStore;
        return null;
    }

    @Override
    public IProgramStageSectionStore getProgramStageSectionStore() {
//        return programStageSectionStore;
        return null;
    }

    @Override
    public IProgramStageStore getProgramStageStore() {
//        return programStageStore;
        return null;
    }

    @Override
    public IProgramRuleStore getProgramRuleStore() {
//        return programRuleStore;
        return null;
    }

    @Override
    public IProgramRuleActionStore getProgramRuleActionStore() {
//        return programRuleActionStore;
        return null;
    }

    @Override
    public IProgramRuleVariableStore getProgramRuleVariableStore() {
//        return programRuleVariableStore;
        return null;
    }

    @Override
    public IIdentifiableObjectStore<RelationshipType> getRelationshipTypeStore() {
//        return relationshipTypeStore;
        return null;
    }

    @Override
    public IDataSetStore getDataStore() {
//        return dataSetStore;
        return null;
    }

    @Override
    public ITrackedEntityAttributeValueStore getTrackedEntityAttributeValueStore() {
//        return trackedEntityAttributeValueStore;
        return null;
    }

    @Override
    public IRelationshipStore getRelationshipStore() {
//        return relationshipStore;
        return null;
    }

    @Override
    public ITrackedEntityInstanceStore getTrackedEntityInstanceStore() {
//        return trackedEntityInstanceStore;
        return null;
    }

    @Override
    public ITrackedEntityDataValueStore getTrackedEntityDataValueStore() {
//        return trackedEntityDataValueStore;
        return null;
    }

    @Override
    public IEventStore getEventStore() {
//        return eventStore;
        return null;
    }

    @Override
    public IEnrollmentStore getEnrollmentStore() {
//        return enrollmentStore;
        return null;
    }

    @Override
    public IIdentifiableObjectStore<Interpretation> getInterpretationStore() {
//        return interpretationStore;
        return null;
    }

    @Override
    public IInterpretationCommentStore getInterpretationCommentStore() {
//        return interpretationCommentStore;
        return null;
    }

    @Override
    public IInterpretationElementStore getInterpretationElementStore() {
//        return interpretationElementStore;
        return null;
    }

    @Override
    public IModelsStore getModelStore() {
//        return modelsStore;
        return null;
    }

    @Override
    public IUserStore getUserStore() {
//        return userStore;
        return null;
    }

    @Override
    public IFailedItemStore getFailedItemStore() {
//        return failedItemStore;
        return null;
    }
}
