/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projectuaspbo.model;
import java.util.Random;
/**
 *
 * @author advan
 */
public class User extends Karakter{
    protected int Magic;
    protected int Healing;
    protected int Block;
    
    public User(String Name, int Health, int Strength,int DamageIncoming, int Magic, int Healing){
        super(Name, Health, Strength,DamageIncoming);
        this.Magic = Magic;
        this.Healing = Healing;
        this.Block = Block;
    }
    
    public int getHealth() {
        return Health;
    }
    
    public int getStrength() {
        return Strength;
    }
    public int getMagic() {
        return Magic;
    }
    public int getHealing() {
        return Healing;
    }
    
    
    public void MagicAttack(Karakter target){
        if(this.Magic >=10){
            target.Health -= (this.Strength*2);
            Magic -= 10;
            System.out.println(this.Name + " Attack " + target.Name + "  with magic and dealing " + this.Strength*2 + " damage!");
        }else{
            System.out.println(this.Name + " tidak memiliki cukup mana untuk menggunakan sihir!");
        }
    }
    public void regenerasi(){
        this.Health += Healing;
        System.out.println(this.Name + " menggunakan regenerasi dan memulihkan " + this.Healing + " kesehatan!");
    }
    
    public void BlockingDamage() {
        Random rand = new Random();
    boolean isBlocked = rand.nextBoolean();

    if (isBlocked) {
        System.out.println(this.Name + " berhasil memblokir serangan!");
        
    } else {
        this.Health -= DamageIncoming; 
        System.out.println(this.Name + " gagal memblokir serangan dan menerima " + DamageIncoming + " damage!");
    }
    }
}
