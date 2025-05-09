# Game BomberMan

## Tác giả 
1. Nguyễn Quốc Trung - 24022474
2. Nguyễn Thiên Trường - 24022480
3. Tống Quang Thái - 24022450

## Mô tả về các đối tượng trong trò chơi 
Chúng được chia làm hai loại chính là nhóm đối tượng động (*Bomber*, *Enemy*, *Bomb*) và nhóm đối tượng tĩnh (*Grass*, *Wall*, *Brick*, *Portal*, *Item*). 
*Nhóm đối tượng động*
- ![](res/sprites/Bomber/koala_down.png) *Bomber* là nhân vật chính của trò chơi. Bomber có thể di chuyển theo 4 hướng trái/phải/lên/xuống theo sự điều khiển của người chơi.
- ![](res/sprites/Enemy/chicken_walk_1.png) *Enemy* là các đối tượng mà Bomber phải tiêu diệt hết để có thể qua Level. Enemy có thể di chuyển ngẫu nhiên hoặc tự đuổi theo Bomber tùy theo loại Enemy. Các loại Enemy sẽ được mô tả cụ thể ở phần dưới.
- ![](res/sprites/Bomb/bomb.png) *Bomb* là đối tượng mà Bomber sẽ đặt và kích hoạt tại các ô Grass. Khi đã được kích hoạt, Bomber và Enemy không thể di chuyển vào vị trí Bomb. Tuy nhiên ngay khi Bomber vừa đặt và kích hoạt Bomb tại ví trí của mình, Bomber có một lần được đi từ vị trí đặt Bomb ra vị trí bên cạnh. Sau khi kích hoạt 3s, Bomb sẽ tự nổ, các đối tượng *Flame* được tạo ra.

*Nhóm đối tượng tĩnh*
- ![](res/sprites/MapBlock/grass.png) *Grass* là đối tượng mà Bomber và Enemy có thể di chuyển xuyên qua, và cho phép đặt Bomb lên vị trí của nó.
- ![](res/sprites/MapBlock/stone.png) *Wall* là đối tượng cố định, không thể phá hủy bằng Bomb cũng như không thể đặt Bomb lên được, Bomber và Enemy không thể di chuyển vào đối tượng này
- ![](res/sprites/MapBlock/brick.png) *Brick* là đối tượng được đặt lên các ô Grass, không cho phép đặt Bomb lên nhưng có thể bị phá hủy bởi Bomb được đặt gần đó. Bomber và Enemy thông thường không thể di chuyển vào vị trí Brick khi nó chưa bị phá hủy.
- ![](res/sprites/MapBlock/gate.png) *Portal* là đối tượng được giấu phía sau một đối tượng Brick. Khi Brick đó bị phá hủy, Portal sẽ hiện ra và nếu tất cả Enemy đã bị tiêu diệt thì người chơi có thể qua Level khác bằng cách di chuyển vào vị trí của Portal.
  
Các *Item* cũng được giấu phía sau Brick và chỉ hiện ra khi Brick bị phá hủy. Bomber có thể sử dụng Item bằng cách di chuyển vào vị trí của Item. Thông tin về chức năng của các Item được liệt kê như dưới đây:
- ![](res/sprites/PowerUp/powerup_speed.png) *SpeedItem* Khi sử dụng Item này, Bomber sẽ được tăng vận tốc di chuyển thêm một giá trị thích hợp.
- ![](res/sprites/PowerUp/powerup_flames.png) *FlameItem* Item này giúp tăng phạm vi ảnh hưởng của Bomb khi nổ (độ dài các Flame lớn hơn).
- ![](res/sprites/PowerUp/powerup_bombs.png) *BombItem* Thông thường, nếu không có đối tượng Bomb nào đang trong trạng thái kích hoạt, Bomber sẽ được đặt và kích hoạt duy nhất một đối tượng Bomb. Item này giúp tăng số lượng Bomb có thể đặt thêm một.

Có 3 loại *Enemy*:
- ![](res/sprites/Enemy/pineapple.png) *Pineapple* là Enemy đơn giản nhất, di chuyển ngẫu nhiên với tốc độ cố định.
- ![](res/sprites/Enemy/chicken_walk_1.png) *Chicken* là Enemy di chuyển thông minh hơn so với Pineapple (biết đuổi theo Bomber).
- ![](res/sprites/Enemy/strawberry_walk_1.png) *Strawberry* là Enemy di chuyển ngẫu nhiên nhưng tốc độ nhanh hơn Pineapple và Chicken.

## Mô tả game play, xử lý va chạm và xử lý bom nổ
- Trong một màn chơi, Bomber sẽ được người chơi di chuyển, đặt và kích hoạt Bomb với mục tiêu chính là tiêu diệt tất cả Enemy và tìm ra vị trí Portal để có thể qua màn mới.
- Bomber sẽ bị giết khi va chạm với Enemy hoặc thuộc phạm vi Bomb nổ. Lúc đấy trò chơi kết thúc.
- Enemy bị tiêu diệt khi thuộc phạm vi Bomb nổ.
- Một đối tượng thuộc phạm vi Bomb nổ có nghĩa là đối tượng đó va chạm với một trong các tia lửa được tạo ra tại thời điểm một đối tượng Bomb nổ.
- Khi Bomb nổ, một Flame trung tâm tại vị trí Bomb nổ và bốn Flame tại bốn vị trí ô đơn vị xung quanh vị trí của Bomb xuất hiện theo bốn hướng trên/dưới/trái/phải. Độ dài bốn Flame xung quanh mặc định là 1 đơn vị, được tăng lên khi Bomber sử dụng các FlameItem.
- Khi các Flame xuất hiện, nếu có một đối tượng thuộc loại Brick/Wall nằm trên vị trí một trong các Flame thì độ dài Flame đó sẽ được giảm đi để sao cho Flame chỉ xuất hiện đến vị trí đối tượng Brick/Wall theo hướng xuất hiện. Lúc đó chỉ có đối tượng Brick/Wall bị ảnh hưởng bởi Flame, các đối tượng tiếp theo không bị ảnh hưởng. Còn nếu vật cản Flame là một đối tượng Bomb khác thì đối tượng Bomb đó cũng sẽ nổ ngay lập tức.

## UML
https://encr.pw/bomberman-uet-UETroach

## Cài đặt

Hướng dẫn:
1. Click chuột phải vào thư mục res/mark directory as/resource root.
2. Click chuột phải vào lib trong thư mục javafx "add as library".
3. Copy đường dẫn của lib trong thư mục javafx sau khi giải nén (sau đây gọi là PATH).
4. Tiếp theo tại nút ⋮\Run with parameters\Modify options\Add VM Options.
5. Điền "--module-path=PATH --add-modules javafx.controls,javafx.fxml,javafx.media".
6. Run.

## Hướng dẫn
![](res/instruction.png)

## Demo
![](res/demo.png)

## Cải tiến trong tương lai
- Thêm các item mới.
- Thêm các enemy mới thông minh hơn.
- Cải thiện đồ họa đẹp và mượt hơn.
- Thêm người chơi.

## Trạng thái dự án
- Đã hoàn thành.
  
## Notes
- Cải tiến dựa trên mã nguồn cũ https://github.com/bt-nghia/bomberman-uet
