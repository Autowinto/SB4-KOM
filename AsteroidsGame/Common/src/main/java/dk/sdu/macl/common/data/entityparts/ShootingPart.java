package dk.sdu.macl.common.data.entityparts;

import dk.sdu.macl.common.data.Entity;
import dk.sdu.macl.common.data.GameData;

public class ShootingPart implements EntityPart {
    float cooldown = 0;
    float cooldownTime;
    boolean isShooting;

    public ShootingPart(float cooldownTime) {
        this.cooldownTime = cooldownTime;
    }

    public void setIsShooting(boolean isShooting) {
        this.isShooting = isShooting;
    }

    public boolean canShoot() {
        if (this.cooldown <= 0) {
            this.cooldown = this.cooldownTime;
            return true;
        }
        return false;
    }

    public boolean getIsShooting() {
        return this.isShooting;
    }
    @Override
    public void process(GameData gameData, Entity entity) {
        cooldown -= gameData.getDelta();
    }
}
