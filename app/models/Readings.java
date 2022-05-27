package models;

import javax.persistence.Entity;

import play.db.jpa.Model;
import java.util.Date;

@Entity
public class Readings extends Model
{
    public Date Date;
    public String Code;
    public float Temp;
    public float Wind_Speed;
    public float Wind_Direction;
    public int Pressure;

    public Readings(Date date, String Code, float Temp, float Wind_Speed, int Pressure)
    {
        this.Date = date;
        this.Code = Code;
        this.Temp = Temp;
        this.Wind_Speed = Wind_Speed;
        this.Pressure = Pressure;
    }

    public Readings(Date date, String Code, float Temp, float Wind_Speed, float Wind_Direction, int Pressure)
    {
        this.Date = date;
        this.Code = Code;
        this.Temp = Temp;
        this.Wind_Speed = Wind_Speed;
        this.Wind_Direction = Wind_Direction;
        this.Pressure = Pressure;
    }
}