package in.ethicstech.database_interaction;

/**
 * Created by krishnan on 11/12/15.
 */
public class Chef_Food_Adapter {

    String chefId,foodId,chefName,foodType,foodName,foodDescription,foodImagePath,foodPrice,foodQuantity,foodAvailableDate;

    Chef_Food_Adapter(String chefId, String foodId, String chefName, String foodType, String foodName, String foodDescription, String foodImagePath, String foodPrice, String foodQuantity, String foodAvailableDate) {
        this.chefId=chefId;
        this.foodId=foodId;
        this.chefName=chefName;
        this.foodType=foodType;
        this.foodName=foodName;
        this.foodDescription=foodDescription;
        this.foodImagePath=foodImagePath;
        this.foodPrice=foodPrice;
        this.foodQuantity=foodQuantity;
        this.foodAvailableDate=foodAvailableDate;
    }

    public String getChefId() {
        return chefId;
    }

    public String getFoodType() {
        return foodType;
    }

    public String getFoodQuantity() {
        return foodQuantity;
    }

    public String getFoodPrice() {
        return foodPrice;
    }

    public String getFoodName() {
        return foodName;
    }

    public String getFoodImagePath() {
        return foodImagePath;
    }

    public String getChefName() {
        return chefName;
    }

    public String getFoodDescription() {
        return foodDescription;
    }

    public String getFoodId() {
        return foodId;
    }

    public String getFoodAvailableDate() {
        return foodAvailableDate;
    }

    public void setChefId(String chefId) {
        this.chefId = chefId;
    }

    public void setChefName(String chefName) {
        this.chefName = chefName;
    }

    public void setFoodAvailableDate(String foodAvailableDate) {
        this.foodAvailableDate = foodAvailableDate;
    }

    public void setFoodDescription(String foodDescription) {
        this.foodDescription = foodDescription;
    }

    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }

    public void setFoodImagePath(String foodImagePath) {
        this.foodImagePath = foodImagePath;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public void setFoodPrice(String foodPrice) {
        this.foodPrice = foodPrice;
    }

    public void setFoodQuantity(String foodQuantity) {
        this.foodQuantity = foodQuantity;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

}
