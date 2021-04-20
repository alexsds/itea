package lesson03;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class Pudge extends Hero implements Externalizable {
    private static final long serialVersionUID = 1L;

    private String ultimate;

    public Pudge(int strength, int agility, int intelligence, String ultimate) {
        super(strength, agility, intelligence);
        this.ultimate = ultimate;
    }

    public Pudge() {
        super(0,0, 0);
        ultimate = "";
    }

    public String getUltimate() {
        return ultimate;
    }

    public void setUltimate(String ultimate) {
        this.ultimate = ultimate;
    }

    @Override
    public String toString() {
        return "Pudge{" +
                "ultimate='" + getUltimate() + '\'' +
                "strength='" + getStrength() + '\'' +
                "agility='" + getAgility() + '\'' +
                "intelligence='" + getIntelligence() + '\'' +
                '}';
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeUTF(getUltimate());
        out.writeInt(getStrength());
        out.writeInt(getAgility());
        out.writeInt(getIntelligence());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        setUltimate(in.readUTF());
        setStrength(in.readInt());
        setAgility(in.readInt());
        setIntelligence(in.readInt());
    }
}
