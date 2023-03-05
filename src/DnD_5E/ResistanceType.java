package DnD_5E;

public enum ResistanceType {

    RES(.5f), IMM(0f), VUL(2f);

    public final float mod;

    ResistanceType(float mod) {
        this.mod = mod;
    }

}
