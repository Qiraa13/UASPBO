/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projectuaspbo.model;

/**
 *
 * @author advan
 */
public class Enemy extends Karakter {
     protected int tingkatKejahatan;
    
    public Enemy(String Name, int Health, int Strength,int DamageIncoming, int tingkatKejahatan){
        super(Name, Health, Strength,DamageIncoming);
        this.tingkatKejahatan = tingkatKejahatan;
    }
    
    public void mengejek(){
        System.out.println(this.Name + " mengejek: 'Kau tak akan pernah bisa mengalahkanku!'");
    }
}
