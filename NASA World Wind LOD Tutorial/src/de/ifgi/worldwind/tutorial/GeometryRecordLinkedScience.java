package de.ifgi.worldwind.tutorial;

public class GeometryRecordLinkedScience  {

        private double deforestation;
        private double pasturePrice;
        private String geometry;

        public double getDeforestation() {
                return deforestation;
        }
        public void setDeforestation(double deforestation) {
                this.deforestation = deforestation;
        }
        public double getPasturePrice() {
                return pasturePrice;
        }
        public void setPasturePrice(double pasturePrice) {
                this.pasturePrice = pasturePrice;
        }
        public String getGeometry() {
                return geometry;
        }
        public void setGeometry(String geometry) {
                this.geometry = geometry;
        }

        public GeometryRecordLinkedScience(double deforestation,
                        double pasturePrice, String geometry) {
                super();
                this.deforestation = deforestation;
                this.pasturePrice = pasturePrice;
                this.geometry = geometry;
        }

}