package enerisan.webapp.dto;


public class UpdateIncidentForm {



        private IncidentWithCategoriesDto incidentWithCategories;
        private IncidentForm incidentForm;


        public UpdateIncidentForm() {
        }

        public IncidentWithCategoriesDto getIncidentWithCategories() {
            return incidentWithCategories;
        }

        public void setIncidentWithCategories(IncidentWithCategoriesDto incidentWithCategories) {
            this.incidentWithCategories = incidentWithCategories;
        }

        public IncidentForm getIncidentForm() {
            return incidentForm;
        }

        public void setIncidentForm(IncidentForm incidentForm) {
            this.incidentForm = incidentForm;
        }

}
