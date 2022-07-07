package com.stslex.core_model.di

import com.stslex.core_model.common.PrimaryMapper
import com.stslex.core_model.mapper.*
import com.stslex.core_model.model.NoteEntity
import com.stslex.core_model.transformer.TransformerEqualTypeValues
import com.stslex.core_model.transformer.TransformerNotesSyncedSize
import org.koin.core.annotation.Module
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

@Module
class MapperModule {

    val module = module {
        singleOf(MapperEntityDynamicUI::Base) { bind<MapperEntityDynamicUI>() }
        singleOf(MapperEntityUI::Base) { bind<MapperEntityUI>() }
        singleOf(MapperNoteUIEntity::Base) { bind<MapperNoteUIEntity>() }
        singleOf(MapperPagingEntityDynamicUI::Base) { bind<MapperPagingEntityDynamicUI>() }
        singleOf(MapperValueNoteEntityUI::Base) { bind<MapperValueNoteEntityUI>() }
        singleOf(MapperNoteRemote::Base) { bind<MapperNoteRemote>() }
        singleOf(MapperNoteListRemote::Base) { bind<MapperNoteListRemote>() }
        singleOf(MapperNoteSize::Base) { bind<MapperNoteSize>() }
        singleOf(TransformerNotesSyncedSize::Base) { bind<TransformerNotesSyncedSize>() }
        singleOf(PrimaryMapper::Base) { bind<PrimaryMapper>() }
        single<TransformerEqualTypeValues<List<NoteEntity>, Int>> {
            TransformerEqualTypeValues.Base(get<TransformerNotesSyncedSize>())
        }
    }
}