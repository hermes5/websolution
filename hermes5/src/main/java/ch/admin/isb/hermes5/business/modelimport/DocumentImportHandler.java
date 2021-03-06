/*----------------------------------------------------------------------------------------------
 * Copyright 2014 Federal IT Steering Unit FITSU Licensed under the Apache License, Version 2.0 (the "License"); you
 * may not use this file except in compliance with the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 *---------------------------------------------------------------------------------------------*/
package ch.admin.isb.hermes5.business.modelimport;

import javax.inject.Inject;

import ch.admin.isb.hermes5.domain.Document;
import ch.admin.isb.hermes5.domain.TranslationDocument;
import ch.admin.isb.hermes5.persistence.db.dao.DocumentDAO;
import ch.admin.isb.hermes5.util.ConfigurationProperty;
import ch.admin.isb.hermes5.util.SystemProperty;

public class DocumentImportHandler extends AbstractTranslationDocumentImportHandler {

    private static final long serialVersionUID = 1L;

    @Inject
    DocumentDAO documentDAO;

    @Inject
    @SystemProperty(value = "documentTypes", fallback = "doc, docx, dot, dotx, xlsx, xls, ppt, pptx")
    ConfigurationProperty documentTypes;

    @Override
    public boolean isResponsible(String filename) {
        String[] urlParts = filename.toLowerCase().split("\\.");
        return documentTypes.getStringValue().contains(urlParts[urlParts.length - 1]);
    }

    @Override
    protected void mergeTranslationDocument(TranslationDocument translationDocument) {
        documentDAO.merge((Document) translationDocument);
    }

    @Override
    protected TranslationDocument readTranslationDocument(String filename, String publishedModelIdentifier) {
        return documentDAO.getDocument(publishedModelIdentifier, filename);
    }

    @Override
    protected TranslationDocument createTranslationDocument(String modelIdentifier, String filename) {
        Document document = new Document();
        document.setModelIdentifier(modelIdentifier);
        document.setUrlDe(filename);
        return document;
    }

}
