package com.stslex.core_model.di

import androidx.paging.PagingData
import com.stslex.core.Mapper
import com.stslex.core.ValueState
import com.stslex.core_model.common.MapperName.*
import com.stslex.core_model.common.PrimaryMapper
import com.stslex.core_model.common.TimeUtil
import com.stslex.core_model.common.TimeUtilImpl
import com.stslex.core_model.mapper.*
import com.stslex.core_model.model.NoteDynamicUI
import com.stslex.core_model.model.NoteEntity
import com.stslex.core_model.model.NoteUI
import com.stslex.core_model.transformer.TransformerEqualTypeValues
import com.stslex.core_model.transformer.TransformerNotesSyncedSize
import org.koin.core.annotation.Module
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.qualifier.named
import org.koin.dsl.module

@Module
class MapperModule {

    val module = module {

        factoryOf(::TimeUtilImpl) { bind<TimeUtil>() }

        factory<Mapper.Data<NoteEntity, NoteDynamicUI>>(
            named(ENTITY_DYNAMIC)
        ) { MapperEntityDynamicUI(get()) }

        factory<Mapper.Data<NoteEntity, NoteUI>>(
            named(ENTITY_UI)
        ) { MapperEntityUI() }

        factory<Mapper.Data<NoteEntity, Map<String, Any>>>(
            named(ENTITY_MAP)
        ) { MapperNoteRemote() }

        factory<Mapper.Data<List<NoteEntity>, Map<String, Any>>>(
            named(ENTITY_MAP_LIST)
        ) { MapperNoteListRemote(get(named(ENTITY_MAP))) }

        factory<Mapper.Data<PagingData<NoteEntity>, PagingData<NoteDynamicUI>>>(
            named(PAGING_ENTITY_DYNAMIC)
        ) {
            MapperPagingEntityDynamicUI(get(named(ENTITY_DYNAMIC)))
        }

        factory<Mapper.Data<NoteUI, NoteEntity>>(named(UI_ENTITY)) {
            MapperNoteUIEntity()
        }

        factory<Mapper.ToUI<NoteEntity, ValueState<NoteUI>>>(named(VALUE_ENTITY_UI)) {
            MapperValueNoteEntityUI(get(named(ENTITY_UI)))
        }

        factory<Mapper.ToUI<List<NoteEntity>, ValueState<Int>>>(named(VALUE_ENTITY_SIZE)) {
            MapperNoteSize()
        }

        factoryOf(TransformerNotesSyncedSize::Base) { bind<TransformerNotesSyncedSize>() }
        factoryOf(PrimaryMapper::Base) { bind<PrimaryMapper>() }
        factory<TransformerEqualTypeValues<List<NoteEntity>, Int>> {
            TransformerEqualTypeValues.Base(get<TransformerNotesSyncedSize>())
        }
    }
}