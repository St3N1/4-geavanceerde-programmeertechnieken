local PositionComponent = luajava.bindClass("dungeon_crawler.components.PositionComponent")
local MovementComponent = luajava.bindClass("dungeon_crawler.components.MovementComponent")
local VelocityComponent = luajava.bindClass("dungeon_crawler.components.VelocityComponent")

local ACTIVE_DISTANCE = 300
local ATTACK_RADIUS = 50

function update(goblin, player, stepTime)
    local goblinPos = goblin:getComponent(PositionComponent):get()
    local playerPos = player:getComponent(PositionComponent):get()
    local goblinMov = goblin:getComponent(MovementComponent):get()
    local goblinVel = goblin:getComponent(VelocityComponent):get()

    if goblinPos == nil or playerPos == nil or goblinMov == nil then return end

    setDirection(playerPos, goblinPos, goblinMov)
    local distance = getPlayerDistance(playerPos, goblinPos)
    if distance <= ACTIVE_DISTANCE then setMovement(goblinPos, goblinMov, goblinVel, stepTime) end
    if distance <= ATTACK_RADIUS then attackPlayer(goblin) end
end

function getPlayerDistance(playerPos, goblinPos)
    local dx = playerPos:getX() - goblinPos:getX()
    local dy = playerPos:getY() - goblinPos:getY()
    return math.sqrt(dx*dx + dy*dy)
end

function setDirection(playerPos, goblinPos, goblinMov)
    local x = 0
    if goblinPos:getX() >= playerPos:getX() then
        x = -1
    else
        x = 1
    end

    local y = 0
    if goblinPos:getY() >= playerPos:getY() then
        y = -1
    else
        y = 1
    end

    goblinMov:setDirection(x, y)
end

function setMovement(goblinPos, goblinMov, goblinVel, stepTime)
    goblinPos:setX(goblinPos:getX() + goblinVel:getDx() * goblinMov:getX() * stepTime);
    goblinPos:setY(goblinPos:getY() + goblinVel:getDy() * goblinMov:getY() * stepTime);
end

function attackPlayer(goblin)
    goblin:attack()
end