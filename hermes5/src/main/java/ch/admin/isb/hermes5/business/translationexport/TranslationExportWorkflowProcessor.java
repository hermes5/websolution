/*----------------------------------------------------------------------------------------------
 * Copyright 2014 Federal IT Steering Unit FITSU Licensed under the Apache License, Version 2.0 (the "License"); you
 * may not use this file except in compliance with the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 *---------------------------------------------------------------------------------------------*/
package ch.admin.isb.hermes5.business.translationexport;

import java.util.List;

import ch.admin.isb.hermes5.domain.TranslationEntity;

public interface TranslationExportWorkflowProcessor {

    String path(String modelIdentifier, TranslationEntity translationEntity, String lang);

    byte[] processEntity(TranslationEntity translationEntity, String lang);

    void markAsInArbeit(TranslationEntity translationEntity, List<String>langs);
    
    boolean isResponsible(TranslationEntity translationEntity);
}
