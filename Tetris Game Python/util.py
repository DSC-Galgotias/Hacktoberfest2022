#Import libraries
import pygame
import sys
import random
import time

# Define important global variables
pygame.init()
clock = pygame.time.Clock()

best_score = 0
longest_time = 0

width = 700
height = 750
DISPLAY_SCREEN = pygame.display.set_mode((width, height))
pygame.display.set_caption(" Tetris")

off_set_x = 10
off_set_y = 80
playing_field_width = 330  #330 / 10 = 33 width per tile
playing_field_height = 660  #600 / 20 = 33 height per tile
tile_length = 33 # tile is a square

#colors
blue = (0, 0, 255)
white = (255, 255, 255) 
black = (0, 0, 0)
gray = (95, 95, 96) 
orange  = (249, 87, 0)  
cobalt_blue = (3, 65, 174)
green_apple  = (114, 203, 59)
cyber_yellow= (255, 213, 0)
beer = (255, 151, 28)
ryb_red = (255, 50, 19)
purple = (128, 0, 128)

# colors of Tetris blocks
block_colors = (cobalt_blue, blue, green_apple, purple, cyber_yellow, beer, ryb_red)
# shapes of Tetris blocks
shapes = ("i_block", "l_block", "j_block", "o_block", "s_block", "t_block", "z_block")
directions = ("vertical_1", "vertical_2", "horizontal_1", "horizontal_2")

background_img = pygame.image.load("resources/images/background_img.jpg")      
instructions_img = pygame.image.load("resources/images/instructions_img.jpg")  
icon_img = pygame.image.load("resources/images/icon.png")
pygame.display.set_icon(icon_img)

class Button:
    def __init__(self, button_color, button_hover_over_color, x, y, width, height, text_size,  text_color, text_hover_over_color = None, text_str=""):
        self.button_color = button_color
        self.button_hover_over_color = button_hover_over_color
        self.x = x
        self.y = y
        self.width = width
        self.height = height
        self.text_size = text_size
        self.text_color = text_color

        if text_hover_over_color:
            self.text_hover_over_color = text_hover_over_color
        else:
            self.text_hover_over_color =  text_color
 
        self.text_str = text_str


    def blit(self, display_screen, outline_color=None):
        if outline_color: 
            pygame.draw.rect(display_screen, outline_color, (self.x-3, self.y-3, self.width+6, self.height+6))
        
        pygame.draw.rect(display_screen, self.button_color, (self.x, self.y, self.width, self.height))

        if self.text_str != "": 
            font = pygame.font.Font("freesansbold.ttf", self.text_size)
            text = font.render(self.text_str, True, self.text_color)
            # to center the text in the middle of the button based on the size of the button
            text_position = (self.x + (self.width/2 - text.get_width()/2), self.y + (self.height/2 - text.get_height()/2))
            display_screen.blit(text, text_position)


    def is_hovered_over(self, mouse_position):
        if self.x < mouse_position[0] < self.x+self.width and self.y < mouse_position[1] < self.y+self.height:
            return True
        return False


    def blit_hovered_over(self, display_screen):
        pygame.draw.rect(display_screen, self.button_hover_over_color, (self.x, self.y, self.width, self.height))

        if self.text_str != "":
            font = pygame.font.Font("freesansbold.ttf", self.text_size)
            text = font.render(self.text_str, True, self.text_hover_over_color)
            # to center the text in the middle of the button based on the size of the button
            text_position = (self.x + (self.width/2 - text.get_width()/2), self.y + (self.height/2 - text.get_height()/2))
            display_screen.blit(text, text_position)


    def is_clicked(self, mouse_position, event):
        if self.is_hovered_over(mouse_position):
            if event.type == pygame.MOUSEBUTTONDOWN and event.button == 1:
                return True
        return False

class Tile:
    def __init__(self, x, y, color = black):
        self.x = x
        self.y = y
        self.color = color
        self.empty = True


    def draw_tile(self):
        pygame.draw.rect(DISPLAY_SCREEN , self.color, (self.x, self.y, tile_length, tile_length) )


class PlayingField():
    def __init__(self):
        #y coordinate of first row = (80) off_set_y 
        self.tiles = {
            "row1":  {80:  []},
            "row2":  {113: []},
            "row3":  {146: []},
            "row4":  {179: []},
            "row5":  {212: []},
            "row6":  {245: []},
            "row7":  {278: []},
            "row8":  {311: []},
            "row9":  {344: []},
            "row10": {377: []},
            "row11": {410: []},
            "row12": {443: []},
            "row13": {476: []},
            "row14": {509: []},
            "row15": {542: []},
            "row16": {575: []},
            "row17": {608: []},
            "row18": {641: []},
            "row19": {674: []},
            "row20": {707: []},
        }
        self.__init_field()


    def __init_field(self):    
        y = off_set_y
        for i in range(20): #rows
            x = off_set_x
            for j in range(10): #cols
                tile_to_add = Tile(x, y) 
                self.tiles["row"+str(i+1)][y].append(tile_to_add)
                x += tile_length
            y += tile_length
    

    def destory_full_row(self, player):
        times = 0
        y = off_set_y        
        for i in range(20):
            for tile in self.tiles["row"+str(i+1)][y]:
                if tile.empty: break

                elif tile.x == off_set_x+playing_field_width-tile_length:
                    times += 1
                    for j in range(800): #just for flashing the row
                        if j%2 == 0:
                            pygame.draw.rect(DISPLAY_SCREEN , black, (self.tiles["row"+str(i+1)][y][0].x+1, self.tiles["row"+str(i+1)][y][0].y+1, playing_field_width-2, tile_length-2) )
                        else:
                            for tile in self.tiles["row"+str(i+1)][y]:
                                pygame.draw.rect(DISPLAY_SCREEN , tile.color, (tile.x, tile.y, tile_length, tile_length) )
                        pygame.draw.line(DISPLAY_SCREEN , white, (off_set_x, y), (playing_field_width+off_set_x-1, y)) # horizontal line
                        pygame.display.update()

                    # let's destory this full row
                    self.destroy_and_replace(i+1, y)
                    player.score += 10*times

            y += tile_length 


    def destroy_and_replace(self, row_number, row_y):
        for i in range (row_number, 1, -1):
            prev_row_number = i-1
            prev_y = row_y-tile_length
            
            self.tiles["row"+str(i)][row_y].clear() #current_row.clear()
            temp_x = off_set_x
            for j in range(10):
                empty_tile = Tile(temp_x, row_y)
                temp_x += tile_length
                self.tiles["row"+str(i)][row_y].append(empty_tile)
            if prev_y < 80: 
                break

            
            for j in range(10):
                old_tile = self.tiles["row"+str(i)][row_y][j]
                new_tile = self.tiles["row"+str(prev_row_number)][prev_y][j] 
                old_tile.x = new_tile.x
                old_tile.color = new_tile.color 
                old_tile.empty = new_tile.empty

            row_y -= tile_length


class Block:
    def __init__(self, shape:str, color = black):
        self.shape = shape
        self.color = color

        self.direction = directions[0] #vertical_1

        #                         tile1                                                        , tile2            , tile3            , tile4
        self.tiles = [ Tile(off_set_x+playing_field_width/2-tile_length, off_set_y, self.color), Tile(0, 0, color), Tile(0, 0, color), Tile(0, 0, color)]
        
        self.__init_shape() 
        for tile in self.tiles:
            tile.empty = False


    def __init_shape(self):
        if self.shape == "i_block":
            self.tiles[1] = Tile(self.tiles[0].x, self.tiles[0].y-tile_length, self.color)
            self.tiles[2] = Tile(self.tiles[0].x, self.tiles[1].y-tile_length, self.color)
            self.tiles[3] = Tile(self.tiles[0].x, self.tiles[2].y-tile_length, self.color)
        elif self.shape == "l_block":
            self.tiles[1] = Tile(self.tiles[0].x+tile_length, self.tiles[0].y, self.color)
            self.tiles[2] = Tile(self.tiles[0].x-tile_length, self.tiles[0].y, self.color)
            self.tiles[3] = Tile(self.tiles[2].x, self.tiles[2].y-tile_length, self.color)
        elif self.shape == "j_block":
            self.tiles[1] = Tile(self.tiles[0].x+tile_length, self.tiles[0].y, self.color)
            self.tiles[2] = Tile(self.tiles[0].x-tile_length, self.tiles[0].y, self.color)
            self.tiles[3] = Tile(self.tiles[1].x, self.tiles[1].y-tile_length, self.color)
        elif self.shape == "o_block":
            self.tiles[1] = Tile(self.tiles[0].x+tile_length,  self.tiles[0].y, self.color)
            self.tiles[2] = Tile(self.tiles[0].x, self.tiles[0].y-tile_length, self.color)
            self.tiles[3] = Tile(self.tiles[1].x, self.tiles[1].y-tile_length, self.color)
        elif self.shape == "s_block":
            self.tiles[1] = Tile(self.tiles[0].x-tile_length, self.tiles[0].y, self.color)
            self.tiles[2] = Tile(self.tiles[0].x, self.tiles[0].y-tile_length, self.color)
            self.tiles[3] = Tile(self.tiles[2].x+tile_length, self.tiles[2].y, self.color)
        elif self.shape == "t_block":
            self.tiles[1] = Tile(self.tiles[0].x+tile_length,  self.tiles[0].y, self.color)
            self.tiles[2] = Tile(self.tiles[0].x-tile_length, self.tiles[0].y, self.color)
            self.tiles[3] = Tile(self.tiles[0].x, self.tiles[0].y-tile_length, self.color)
        elif self.shape == "z_block":
            self.tiles[1] = Tile(self.tiles[0].x+tile_length,  self.tiles[0].y, self.color)
            self.tiles[2] = Tile(self.tiles[0].x, self.tiles[0].y-tile_length, self.color)
            self.tiles[3] = Tile(self.tiles[2].x-tile_length, self.tiles[2].y, self.color)
        else:
            print("Error: wrong block name.")
            pygame.quit()
            sys.exit()

    
    def complete_block(self):
        self.__init_shape()


    def can_fall(self, next_block, playing_field, player):
        from tetris import manage_events, update_graphics
        manage_events(self, next_block, playing_field, player)
        #check borders
        for block_tile in self.tiles:
            if block_tile.y >= playing_field_height+off_set_y-tile_length:
                return False  

        #check already existed tiles
        for block_tile in self.tiles:
            y = off_set_y
            for i in range(20):
                for tile in playing_field.tiles["row"+str(i+1)][y]:
                    if not tile.empty and block_tile.y+tile_length == tile.y and block_tile.x == tile.x: 
                        return False  
                y += tile_length
    
        return True


    def block_is_falling(self, next_block, playing_field, player, faster=None):
        from tetris import manage_events, update_graphics
        manage_events(self,next_block, playing_field, player)

        if self.can_fall(next_block, playing_field, player):
            for tile in self.tiles:
                tile.y += tile_length

            manage_events(self, next_block, playing_field, player)             
            update_graphics(self, next_block, playing_field, player)
            if faster:                
                clock.tick(40)
                self.block_is_falling( next_block, playing_field, player)
            else:                
                clock.tick(5)
            manage_events(self, next_block, playing_field, player)             
            update_graphics(self, next_block, playing_field, player)


    def get_new_block(self, next_block, playing_field, player):
        if self.can_fall(next_block, playing_field, player): return (self, next_block, False)
        
        #if the block has falled completely
        for block_tile in self.tiles: 
            found = False 
            y = off_set_y
            for i in range(20):
                if not found:
                    for j in range(10):
                        if block_tile.x == playing_field.tiles["row"+str(i+1)][y][j].x and block_tile.y == playing_field.tiles["row"+str(i+1)][y][j].y:
                            playing_field.tiles["row"+str(i+1)][y][j].color = block_tile.color
                            playing_field.tiles["row"+str(i+1)][y][j].empty = False
                            found = True
                            break
                    y += tile_length
                else:
                    break
        
        new_block = next_block

        next_rand_index1 = random.randint(0, 6)
        next_rand_index2 = random.randint(0, 6)
        new_next_block = Block(shapes[next_rand_index1], block_colors[next_rand_index2])

        clock.tick(2)
        return (new_block, new_next_block, True)


    def move_left(self, playing_field):
        if self.can_move_left(playing_field):
            for tile in self.tiles:
                tile.x -= tile_length


    def move_right(self, playing_field):
        if self.can_move_right(playing_field):
            for tile in self.tiles:
                tile.x += tile_length


    def can_move_left(self, playing_field):
        # whether inside the playing field or not
        for tile in self.tiles:
            if tile.x <= off_set_x:
                return False
        # whether adjacent field_tiles are occupied or not
        for block_tile in self.tiles:
            y = off_set_y
            for i in range(20):
                for tile in playing_field.tiles["row"+str(i+1)][y]:
                    if not tile.empty and block_tile.x-tile_length == tile.x and block_tile.y  == tile.y:
                        return False  
                y += tile_length
        return True
        

    def can_move_right(self, playing_field):
        # whether inside the playing field or not
        for tile in self.tiles:
            if tile.x + tile_length >= off_set_x+playing_field_width:
                return False
        # whether adjacent field_tiles are occupied or not
        for block_tile in self.tiles:
            y = off_set_y
            for i in range(20):
                for tile in playing_field.tiles["row"+str(i+1)][y]:
                    if not tile.empty and block_tile.x+tile_length == tile.x and block_tile.y  == tile.y:
                        return False  
                y += tile_length
        return True


    def rotate(self, next_block, playing_field, player):
        from tetris import manage_events, update_graphics
        manage_events(self, next_block, playing_field, player)

        if self.shape == "i_block":
            self.rotate_i_block(playing_field)
        elif self.shape == "l_block":
            self.rotate_l_block(playing_field)
        elif self.shape == "j_block":
            self.rotate_j_block(playing_field)
        elif self.shape == "o_block":
            return
            #no rotation for o_block.
        elif self.shape == "s_block":
            self.rotate_s_block(playing_field)
        elif self.shape == "t_block":
            self.rotate_t_block(playing_field)
        elif self.shape == "z_block":
            self.rotate_z_block(playing_field)
        else:
            print("Error: wrong block name.")
            pygame.quit()
            sys.exit()
        manage_events(self, next_block, playing_field, player)
        update_graphics(self, next_block, playing_field, player)


    def rotate_i_block(self, playing_field): #done
        temp_rotated_i = Block("i_block", self.color)
        temp_rotated_i.tiles = self.tiles.copy()
        
        if self.direction == directions[0] or self.direction == directions[1]:
            # ----
            temp_rotated_i.tiles[0] = Tile(temp_rotated_i.tiles[1].x, temp_rotated_i.tiles[0].y, temp_rotated_i.color) 
            temp_rotated_i.tiles[1] = Tile(temp_rotated_i.tiles[0].x-tile_length, temp_rotated_i.tiles[0].y, temp_rotated_i.color)
            temp_rotated_i.tiles[2] = Tile(temp_rotated_i.tiles[0].x+tile_length, temp_rotated_i.tiles[0].y, temp_rotated_i.color)
            temp_rotated_i.tiles[3] = Tile(temp_rotated_i.tiles[2].x+tile_length, temp_rotated_i.tiles[0].y, temp_rotated_i.color)
            temp_rotated_i.direction = directions[2] # "horizontal_1"
        elif self.direction == directions[2] or self.direction == directions[3]:
            # |
            # |
            # |
            # |
            temp_rotated_i.tiles[1] = Tile(temp_rotated_i.tiles[0].x, temp_rotated_i.tiles[0].y-tile_length, temp_rotated_i.color)
            temp_rotated_i.tiles[2] = Tile(temp_rotated_i.tiles[1].x, temp_rotated_i.tiles[1].y-tile_length, temp_rotated_i.color)
            temp_rotated_i.tiles[3] = Tile(temp_rotated_i.tiles[2].x, temp_rotated_i.tiles[2].y-tile_length, temp_rotated_i.color)
            temp_rotated_i.direction = directions[0] #"vertical_1"

        for block_tile in temp_rotated_i.tiles:
            if block_tile.x <= off_set_x or block_tile.x >= playing_field_width:
                return 
            y = off_set_y
            for i in range(20):
                for tile in playing_field.tiles["row"+str(i+1)][y]:
                    if not tile.empty and block_tile.x == tile.x and block_tile.y  == tile.y:
                        return
                y += tile_length

        self.direction = temp_rotated_i.direction
        self.tiles = temp_rotated_i.tiles


    def rotate_l_block(self, playing_field): #done
        temp_rotated_l = Block("l_block", self.color)
        temp_rotated_l.tiles = self.tiles.copy()
        
        if self.direction == directions[0]:
            # after rotating, the block should look like this ↓
            #  _
            # |
            # |
            # |
            temp_rotated_l.tiles[0] = Tile(temp_rotated_l.tiles[0].x, temp_rotated_l.tiles[0].y, temp_rotated_l.color)
            temp_rotated_l.tiles[1] = Tile(temp_rotated_l.tiles[0].x, temp_rotated_l.tiles[0].y-tile_length, temp_rotated_l.color)
            temp_rotated_l.tiles[2] = Tile(temp_rotated_l.tiles[1].x, temp_rotated_l.tiles[1].y-tile_length, temp_rotated_l.color)
            temp_rotated_l.tiles[3] = Tile(temp_rotated_l.tiles[2].x+tile_length, temp_rotated_l.tiles[2].y, temp_rotated_l.color)
            temp_rotated_l.direction = directions[2] # "horizontal_1"
        elif self.direction == directions[2]:
            # after rotating, the block should look like this ↓
            # ---
            #   |
            temp_rotated_l.tiles[0] = Tile(temp_rotated_l.tiles[3].x, temp_rotated_l.tiles[0].y, temp_rotated_l.color)
            temp_rotated_l.tiles[1] = Tile(temp_rotated_l.tiles[0].x, temp_rotated_l.tiles[0].y-tile_length, temp_rotated_l.color)
            temp_rotated_l.tiles[2] = Tile(temp_rotated_l.tiles[1].x-tile_length, temp_rotated_l.tiles[1].y, temp_rotated_l.color)
            temp_rotated_l.tiles[3] = Tile(temp_rotated_l.tiles[2].x-tile_length, temp_rotated_l.tiles[2].y, temp_rotated_l.color)
            temp_rotated_l.direction = directions[1] #"vertical_2"
        elif self.direction == directions[1]: 
            # after rotating, the block should look like this ↓
            #  |
            #  |
            # _|
            temp_rotated_l.tiles[0] = Tile(temp_rotated_l.tiles[3].x, temp_rotated_l.tiles[0].y, temp_rotated_l.color)
            temp_rotated_l.tiles[1] = Tile(temp_rotated_l.tiles[0].x+tile_length, temp_rotated_l.tiles[0].y, temp_rotated_l.color)
            temp_rotated_l.tiles[2] = Tile(temp_rotated_l.tiles[1].x, temp_rotated_l.tiles[1].y-tile_length, temp_rotated_l.color)
            temp_rotated_l.tiles[3] = Tile(temp_rotated_l.tiles[2].x, temp_rotated_l.tiles[2].y-tile_length, temp_rotated_l.color)
            temp_rotated_l.direction = directions[3] #"horizontal_2"
        elif self.direction == directions[3]: 
            # after rotating, the block should look like this ↓
            # |
            # ---
            temp_rotated_l.tiles[0] = Tile(temp_rotated_l.tiles[1].x, temp_rotated_l.tiles[0].y, temp_rotated_l.color)
            temp_rotated_l.tiles[1] = Tile(temp_rotated_l.tiles[0].x+tile_length, temp_rotated_l.tiles[0].y, temp_rotated_l.color)
            temp_rotated_l.tiles[2] = Tile(temp_rotated_l.tiles[0].x-tile_length, temp_rotated_l.tiles[1].y, temp_rotated_l.color)
            temp_rotated_l.tiles[3] = Tile(temp_rotated_l.tiles[2].x, temp_rotated_l.tiles[2].y-tile_length, temp_rotated_l.color) 
            temp_rotated_l.direction = directions[0] #"vertical_1"

        for block_tile in temp_rotated_l.tiles:
            if block_tile.x <= off_set_x or block_tile.x >= playing_field_width:
                return 
            y = off_set_y
            for i in range(20):
                for tile in playing_field.tiles["row"+str(i+1)][y]:
                    if not tile.empty and block_tile.x == tile.x and block_tile.y  == tile.y:
                        return
                y += tile_length

        self.direction = temp_rotated_l.direction
        self.tiles = temp_rotated_l.tiles
        

    def rotate_j_block(self, playing_field): #done
        temp_rotated_j = Block("j_block", self.color)
        temp_rotated_j.tiles = self.tiles.copy()
        
        if self.direction == directions[0]: 
            temp_rotated_j.tiles[0] = Tile(temp_rotated_j.tiles[1].x, temp_rotated_j.tiles[0].y, temp_rotated_j.color)
            temp_rotated_j.tiles[1] = Tile(temp_rotated_j.tiles[0].x-tile_length, temp_rotated_j.tiles[0].y, temp_rotated_j.color)
            temp_rotated_j.tiles[2] = Tile(temp_rotated_j.tiles[1].x, temp_rotated_j.tiles[1].y-tile_length, temp_rotated_j.color)
            temp_rotated_j.tiles[3] = Tile(temp_rotated_j.tiles[2].x, temp_rotated_j.tiles[2].y-tile_length, temp_rotated_j.color)
            temp_rotated_j.direction = directions[2] # "horizontal_1"
        elif self.direction == directions[2]:
            temp_rotated_j.tiles[0] = Tile(temp_rotated_j.tiles[1].x-tile_length, temp_rotated_j.tiles[0].y, temp_rotated_j.color)
            temp_rotated_j.tiles[1] = Tile(temp_rotated_j.tiles[0].x, temp_rotated_j.tiles[0].y-tile_length, temp_rotated_j.color)
            temp_rotated_j.tiles[2] = Tile(temp_rotated_j.tiles[1].x+tile_length, temp_rotated_j.tiles[1].y, temp_rotated_j.color)
            temp_rotated_j.tiles[3] = Tile(temp_rotated_j.tiles[2].x+tile_length, temp_rotated_j.tiles[2].y, temp_rotated_j.color)
            temp_rotated_j.direction = directions[1] #"vertical_2"
        elif self.direction == directions[1]: 
            temp_rotated_j.tiles[0] = Tile(temp_rotated_j.tiles[2].x, temp_rotated_j.tiles[0].y, temp_rotated_j.color)
            temp_rotated_j.tiles[1] = Tile(temp_rotated_j.tiles[0].x, temp_rotated_j.tiles[0].y-tile_length, temp_rotated_j.color)
            temp_rotated_j.tiles[2] = Tile(temp_rotated_j.tiles[1].x, temp_rotated_j.tiles[1].y-tile_length, temp_rotated_j.color)
            temp_rotated_j.tiles[3] = Tile(temp_rotated_j.tiles[2].x-tile_length, temp_rotated_j.tiles[2].y, temp_rotated_j.color)
            temp_rotated_j.direction = directions[3] #"horizontal_2"
        elif self.direction == directions[3]: #back to normal:
            temp_rotated_j.tiles[0] = Tile(temp_rotated_j.tiles[0].x, temp_rotated_j.tiles[0].y, temp_rotated_j.color)
            temp_rotated_j.tiles[1] = Tile(temp_rotated_j.tiles[0].x+tile_length, temp_rotated_j.tiles[0].y, temp_rotated_j.color)
            temp_rotated_j.tiles[2] = Tile(temp_rotated_j.tiles[0].x-tile_length, temp_rotated_j.tiles[0].y, temp_rotated_j.color)
            temp_rotated_j.tiles[3] = Tile(temp_rotated_j.tiles[1].x, temp_rotated_j.tiles[1].y-tile_length, temp_rotated_j.color) 
            temp_rotated_j.direction = directions[0] #"vertical_1"

        for block_tile in temp_rotated_j.tiles:
            if block_tile.x <= off_set_x or block_tile.x >= playing_field_width:
                return 
            y = off_set_y
            for i in range(20):
                for tile in playing_field.tiles["row"+str(i+1)][y]:
                    if not tile.empty and block_tile.x == tile.x and block_tile.y  == tile.y:
                        return 
                y += tile_length

        self.direction = temp_rotated_j.direction
        self.tiles = temp_rotated_j.tiles


    def rotate_s_block(self, playing_field): #done
        temp_rotated_s = Block("s_block", self.color)
        temp_rotated_s.tiles = self.tiles.copy()
        
        if self.direction == directions[0] or self.direction == directions[1]:
            temp_rotated_s.tiles[0] = Tile(temp_rotated_s.tiles[3].x, temp_rotated_s.tiles[0].y, temp_rotated_s.color)
            temp_rotated_s.tiles[1] = Tile(temp_rotated_s.tiles[0].x, temp_rotated_s.tiles[0].y-tile_length, temp_rotated_s.color)
            temp_rotated_s.tiles[2] = Tile(temp_rotated_s.tiles[1].x-tile_length, temp_rotated_s.tiles[1].y, temp_rotated_s.color)
            temp_rotated_s.tiles[3] = Tile(temp_rotated_s.tiles[2].x, temp_rotated_s.tiles[2].y-tile_length, temp_rotated_s.color)
            temp_rotated_s.direction = directions[2] # "horizontal_1"
        elif self.direction == directions[2] or self.direction == directions[3]
            temp_rotated_s.tiles[0] = Tile(temp_rotated_s.tiles[2].x, temp_rotated_s.tiles[0].y, temp_rotated_s.color)
            temp_rotated_s.tiles[1] = Tile(temp_rotated_s.tiles[0].x-tile_length, temp_rotated_s.tiles[0].y, temp_rotated_s.color)
            temp_rotated_s.tiles[2] = Tile(temp_rotated_s.tiles[0].x, temp_rotated_s.tiles[0].y-tile_length, temp_rotated_s.color)
            temp_rotated_s.tiles[3] = Tile(temp_rotated_s.tiles[2].x+tile_length, temp_rotated_s.tiles[2].y, temp_rotated_s.color)
            temp_rotated_s.direction = directions[0] #"vertical_1"

        for block_tile in temp_rotated_s.tiles:
            if block_tile.x <= off_set_x or block_tile.x >= playing_field_width:
                return 
            y = off_set_y
            for i in range(20):
                for tile in playing_field.tiles["row"+str(i+1)][y]:
                    if not tile.empty and block_tile.x == tile.x and block_tile.y  == tile.y:
                        return
                y += tile_length

        self.direction = temp_rotated_s.direction
        self.tiles = temp_rotated_s.tiles   


    def rotate_t_block(self, playing_field): #done
        temp_rotated_t = Block("j_block", self.color)
        temp_rotated_t.tiles = self.tiles.copy()
        
        if self.direction == directions[0]: 
            temp_rotated_t.tiles[0] = Tile(temp_rotated_t.tiles[0].x, temp_rotated_t.tiles[0].y, temp_rotated_t.color)
            temp_rotated_t.tiles[1] = Tile(temp_rotated_t.tiles[0].x, temp_rotated_t.tiles[0].y-tile_length, temp_rotated_t.color)
            temp_rotated_t.tiles[2] = Tile(temp_rotated_t.tiles[1].x, temp_rotated_t.tiles[1].y-tile_length, temp_rotated_t.color)
            temp_rotated_t.tiles[3] = Tile(temp_rotated_t.tiles[1].x+tile_length, temp_rotated_t.tiles[1].y, temp_rotated_t.color)
            temp_rotated_t.direction = directions[2] # "horizontal_1"
        elif self.direction == directions[2]:
            temp_rotated_t.tiles[0] = Tile(temp_rotated_t.tiles[0].x, temp_rotated_t.tiles[0].y, temp_rotated_t.color)
            temp_rotated_t.tiles[1] = Tile(temp_rotated_t.tiles[0].x, temp_rotated_t.tiles[0].y-tile_length, temp_rotated_t.color)
            temp_rotated_t.tiles[2] = Tile(temp_rotated_t.tiles[1].x-tile_length, temp_rotated_t.tiles[1].y, temp_rotated_t.color)
            temp_rotated_t.tiles[3] = Tile(temp_rotated_t.tiles[1].x+tile_length, temp_rotated_t.tiles[2].y, temp_rotated_t.color)
            temp_rotated_t.direction = directions[1] #"vertical_2"
        elif self.direction == directions[1]:
            temp_rotated_t.tiles[0] = Tile(temp_rotated_t.tiles[0].x, temp_rotated_t.tiles[0].y, temp_rotated_t.color)
            temp_rotated_t.tiles[1] = Tile(temp_rotated_t.tiles[0].x, temp_rotated_t.tiles[0].y-tile_length, temp_rotated_t.color)
            temp_rotated_t.tiles[2] = Tile(temp_rotated_t.tiles[1].x, temp_rotated_t.tiles[1].y-tile_length, temp_rotated_t.color)
            temp_rotated_t.tiles[3] = Tile(temp_rotated_t.tiles[1].x-tile_length, temp_rotated_t.tiles[1].y, temp_rotated_t.color)
            temp_rotated_t.direction = directions[3] #"horizontal_2"
        elif self.direction == directions[3]: #back to normal:
            temp_rotated_t.tiles[0] = Tile(temp_rotated_t.tiles[0].x, temp_rotated_t.tiles[0].y, temp_rotated_t.color)
            temp_rotated_t.tiles[1] = Tile(temp_rotated_t.tiles[0].x+tile_length, temp_rotated_t.tiles[0].y, temp_rotated_t.color)
            temp_rotated_t.tiles[2] = Tile(temp_rotated_t.tiles[0].x-tile_length, temp_rotated_t.tiles[0].y, temp_rotated_t.color)
            temp_rotated_t.tiles[3] = Tile(temp_rotated_t.tiles[0].x, temp_rotated_t.tiles[0].y-tile_length, temp_rotated_t.color) 
            temp_rotated_t.direction = directions[0] #"vertical_1"

        for block_tile in temp_rotated_t.tiles:
            if block_tile.x <= off_set_x or block_tile.x >= playing_field_width:
                return 
            y = off_set_y
            for i in range(20):
                for tile in playing_field.tiles["row"+str(i+1)][y]:
                    if not tile.empty and block_tile.x == tile.x and block_tile.y  == tile.y:
                        return
                y += tile_length

        self.direction = temp_rotated_t.direction
        self.tiles = temp_rotated_t.tiles


    def rotate_z_block(self, playing_field): #done
        temp_rotated_z = Block("z_block", self.color)
        temp_rotated_z.tiles = self.tiles.copy()
        
        if self.direction == directions[0] or self.direction == directions[1]:

            temp_rotated_z.tiles[0] = Tile(temp_rotated_z.tiles[3].x, temp_rotated_z.tiles[0].y, temp_rotated_z.color)
            temp_rotated_z.tiles[1] = Tile(temp_rotated_z.tiles[0].x, temp_rotated_z.tiles[0].y-tile_length, temp_rotated_z.color)
            temp_rotated_z.tiles[2] = Tile(temp_rotated_z.tiles[1].x+tile_length, temp_rotated_z.tiles[1].y, temp_rotated_z.color)
            temp_rotated_z.tiles[3] = Tile(temp_rotated_z.tiles[2].x, temp_rotated_z.tiles[2].y-tile_length, temp_rotated_z.color)
            temp_rotated_z.direction = directions[2] # "horizontal_1"
        elif self.direction == directions[2] or self.direction == directions[3]:
            temp_rotated_z.tiles[0] = Tile(temp_rotated_z.tiles[3].x, temp_rotated_z.tiles[0].y, temp_rotated_z.color)
            temp_rotated_z.tiles[1] = Tile(temp_rotated_z.tiles[0].x+tile_length, temp_rotated_z.tiles[0].y, temp_rotated_z.color)
            temp_rotated_z.tiles[2] = Tile(temp_rotated_z.tiles[0].x, temp_rotated_z.tiles[0].y-tile_length, temp_rotated_z.color)
            temp_rotated_z.tiles[3] = Tile(temp_rotated_z.tiles[2].x-tile_length, temp_rotated_z.tiles[2].y, temp_rotated_z.color)
            temp_rotated_z.direction = directions[0] #"vertical_1"

        for block_tile in temp_rotated_z.tiles:
            if block_tile.x <= off_set_x or block_tile.x >= playing_field_width:
                return 
            y = off_set_y
            for i in range(20):
                for tile in playing_field.tiles["row"+str(i+1)][y]:
                    if not tile.empty and block_tile.x == tile.x and block_tile.y  == tile.y:
                        return
                y += tile_length

        self.direction = temp_rotated_z.direction
        self.tiles = temp_rotated_z.tiles      

    def fall_completely(self, next_block, playing_field, player):
        from tetris import update_graphics

        fall= True
        while fall:
            for block_tile in self.tiles:
                if block_tile.y >= playing_field_height+off_set_y-tile_length:
                    fall = False  
                    break 

            #check already existed tiles
            for block_tile in self.tiles:
                y = off_set_y
                for i in range(20):
                    for tile in playing_field.tiles["row"+str(i+1)][y]:
                        if not tile.empty and block_tile.y+tile_length == tile.y and block_tile.x == tile.x: 
                            fall = False   
                            break
                    y += tile_length
            
            if not fall:
                break
            
            for tile in self.tiles:
                tile.y += tile_length
            
            update_graphics(self, next_block, playing_field, player)
            clock.get_rawtime()
            clock.tick(50)
            

class Player:
    def __init__(self, start_time):
        self.start_time = start_time

        self.time_since_start = 0
        self.score = 0 
