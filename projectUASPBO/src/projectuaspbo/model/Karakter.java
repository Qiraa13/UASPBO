/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projectuaspbo.model;

/**
 *
 * @author advan
 */
public class Karakter {
    protected String Name;
    public int Health;
    protected int Strength;
    protected int DamageIncoming;
    
    public Karakter(String Name, int Health, int Strength,int DamageIncoming){
        this.Name = Name;
        this.Health = Health;
        this.Strength = Strength;
        this.DamageIncoming = DamageIncoming;
    }
    
    public int getHealth() {
        return Health;
    }

    public int getStrength() {
        return Strength;
    }

    public String getName() {
        return Name;
    }
    
    public void Attack(Karakter target){
        target.Health -= this.Strength;
        System.out.println(this.Name + " Attack " + target.Name + " and dealing " + this.Strength + " Damage!");
    }
    
    public boolean isLive(){
        return (Health > 0)? true:false;
    }
}
