package sntchk.horsestonks.mixin;

import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.horse.AbstractHorseEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractHorseEntity.class)
public abstract class HorseOffspringAttributeMixin extends AnimalEntity {

    protected HorseOffspringAttributeMixin(EntityType<? extends AnimalEntity> p_i48568_1_, World p_i48568_2_) {
        super(p_i48568_1_, p_i48568_2_);
    }

    @Inject(method="setOffspringAttributes", at = @At(value = "TAIL"))
    protected void onSetOffspringAttributes(AgeableEntity mate, AbstractHorseEntity child, CallbackInfo ci){
        //Health
        double minHealth = Math.min(this.getAttributeBaseValue(Attributes.MAX_HEALTH), mate.getAttributeBaseValue(Attributes.MAX_HEALTH));
        if(child.getAttributeBaseValue(Attributes.MAX_HEALTH) < minHealth)
            child.getAttribute(Attributes.MAX_HEALTH).setBaseValue(minHealth);
        //Jump
        double minJumpStrength = Math.min(this.getAttributeBaseValue(Attributes.JUMP_STRENGTH), mate.getAttributeBaseValue(Attributes.JUMP_STRENGTH));
        if(child.getAttributeBaseValue(Attributes.JUMP_STRENGTH) < minJumpStrength)
            child.getAttribute(Attributes.JUMP_STRENGTH).setBaseValue(minJumpStrength);
        //Movement
        double minMovementSpeed = Math.min(this.getAttributeBaseValue(Attributes.MOVEMENT_SPEED), mate.getAttributeBaseValue(Attributes.MOVEMENT_SPEED));
        if(child.getAttributeBaseValue(Attributes.MOVEMENT_SPEED) < minMovementSpeed)
            child.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(minMovementSpeed);

/*
        System.out.printf("Health: A(%g) + B(%g) --> C(%g)\n", this.getAttributeBaseValue(Attributes.MAX_HEALTH), mate.getAttributeBaseValue(Attributes.MAX_HEALTH), child.getAttributeBaseValue(Attributes.MAX_HEALTH));
        System.out.printf("Jump:   A(%g) + B(%g) --> C(%g)\n", this.getAttributeBaseValue(Attributes.JUMP_STRENGTH), mate.getAttributeBaseValue(Attributes.JUMP_STRENGTH), child.getAttributeBaseValue(Attributes.JUMP_STRENGTH));
        System.out.printf("Speed:  A(%g) + B(%g) --> C(%g)\n", this.getAttributeBaseValue(Attributes.MOVEMENT_SPEED), mate.getAttributeBaseValue(Attributes.MOVEMENT_SPEED), child.getAttributeBaseValue(Attributes.MOVEMENT_SPEED));
*/

    }
}
