local PositionComponent = luajava.bindClass("dungeon_crawler.components.PositionComponent")
local MovementComponent = luajava.bindClass("dungeon_crawler.components.MovementComponent")
local VelocityComponent = luajava.bindClass("dungeon_crawler.components.VelocityComponent")

local ACTIVE_DISTANCE = 300
local KEEP_DISTANCE_RADIUS = 100
local ATTACK_RADIUS = 200

function update(skeleton, player, stepTime)
    local skeletonPos = skeleton:getComponent(PositionComponent):get()
    local playerPos = player:getComponent(PositionComponent):get()
    local skeletonMov = skeleton:getComponent(MovementComponent):get()
    local skeletonVel = skeleton:getComponent(VelocityComponent):get()

    if skeletonPos == nil or playerPos == nil or skeletonMov == nil then return end

    setDirection(playerPos, skeletonPos, skeletonMov)
    local distance = getPlayerDistance(playerPos, skeletonPos)
    if distance <= ACTIVE_DISTANCE then setMovement(playerPos, skeletonPos, skeletonVel, stepTime) end
    if distance <= ATTACK_RADIUS then attackPlayer(skeleton, playerPos) end
end

function getPlayerDistance(playerPos, skeletonPos)
    local dx = playerPos:getX() - skeletonPos:getX()
    local dy = playerPos:getY() - skeletonPos:getY()
    return math.sqrt(dx*dx + dy*dy)
end

function setDirection(playerPos, skeletonPos, skeletonMov)
    local x = 0
    if skeletonPos:getX() >= playerPos:getX() then
        x = -1
    else
        x = 1
    end

    local y = 0
    if skeletonPos:getY() >= playerPos:getY() then
        y = -1
    else
        y = 1
    end

    skeletonMov:setDirection(x, y)
end

function setMovement(playerPos, skeletonPos, skeletonVel, stepTime)
    local dx = playerPos:getX() - skeletonPos:getX()
    local dy = playerPos:getY() - skeletonPos:getY()

    local distance = math.sqrt(dx * dx + dy * dy)
    local dirX = dx / distance
    local dirY = dy / distance

    if distance > KEEP_DISTANCE_RADIUS then
        skeletonPos:setX(skeletonPos:getX() + skeletonVel:getDx() * dirX * stepTime);
        skeletonPos:setY(skeletonPos:getY() + skeletonVel:getDy() * dirY * stepTime);
    else
        skeletonPos:setX(skeletonPos:getX() - skeletonVel:getDx() * dirX * stepTime);
        skeletonPos:setY(skeletonPos:getY() - skeletonVel:getDy() * dirY * stepTime);    
    end
end

function attackPlayer(skeleton, playerPos)
    skeleton:shootArrow(playerPos:getX(), playerPos:getY())
end