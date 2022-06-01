package com.stslex.core_model.di

import com.stslex.core_model.mapper.*
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val mapperModule = module {
    singleOf(MapperEntityDynamicUI::Base) { bind<MapperEntityDynamicUI>() }
    singleOf(MapperEntityUI::Base) { bind<MapperEntityUI>() }
    singleOf(MapperNoteUIEntity::Base) { bind<MapperNoteUIEntity>() }
    singleOf(MapperPagingEntityDynamicUI::Base) { bind<MapperPagingEntityDynamicUI>() }
    singleOf(MapperValueNoteEntityUI::Base) { bind<MapperValueNoteEntityUI>() }
    singleOf(MapperNoteRemote::Base) { bind<MapperNoteRemote>() }
    singleOf(MapperNoteListRemote::Base) { bind<MapperNoteListRemote>() }
}