/*
 *   Copyright 2021 SanityCh3ck
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package sntchk.horsestonks.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.HorseBaseEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HorseBaseEntity.class)
public abstract class HorseChildAttributeMixin extends AnimalEntity {

    protected HorseChildAttributeMixin(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "setChildAttributes", at = @At(value = "TAIL"))
    protected void onSetChildAttributes(PassiveEntity mate, HorseBaseEntity child, CallbackInfo ci) {
        //Health
        double minHealth = Math.min(this.getAttributeBaseValue(EntityAttributes.GENERIC_MAX_HEALTH), mate.getAttributeBaseValue(EntityAttributes.GENERIC_MAX_HEALTH));
        if(child.getAttributeBaseValue(EntityAttributes.GENERIC_MAX_HEALTH) < minHealth)
            child.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(minHealth);
        //Jump
        double minJumpStrength = Math.min(this.getAttributeBaseValue(EntityAttributes.HORSE_JUMP_STRENGTH), mate.getAttributeBaseValue(EntityAttributes.HORSE_JUMP_STRENGTH));
        if(child.getAttributeBaseValue(EntityAttributes.HORSE_JUMP_STRENGTH) < minJumpStrength)
            child.getAttributeInstance(EntityAttributes.HORSE_JUMP_STRENGTH).setBaseValue(minJumpStrength);
        //Movement
        double minMovementSpeed = Math.min(this.getAttributeBaseValue(EntityAttributes.GENERIC_MOVEMENT_SPEED), mate.getAttributeBaseValue(EntityAttributes.GENERIC_MOVEMENT_SPEED));
        if(child.getAttributeBaseValue(EntityAttributes.GENERIC_MOVEMENT_SPEED) < minMovementSpeed)
            child.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED).setBaseValue(minMovementSpeed);
    }
}
