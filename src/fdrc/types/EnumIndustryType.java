package fdrc.types;

    public enum EnumIndustryType {
        none("None"),
        retail("Retail"),
        moto("Moto"),
        ecommerce("Ecommerce"),
        autorental("Autorental"),
        lodging("Lodging"),
        restaurant("Restaurant"),
        supermarket("Supermarket"),
        fuel("Fuel");

        public String val;

        EnumIndustryType(String val) {
            this.val = val;
        }
    }

