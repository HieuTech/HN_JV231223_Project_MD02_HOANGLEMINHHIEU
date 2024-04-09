package bussiness.design;

public interface IGeneric <U, E, D>{

    U findById(D id);

    void addNewELement();
    void editElement();
    void deleteElement();

    //ko để displayAllData làm static, vì các stream cần truy cập để hiển thị

}
