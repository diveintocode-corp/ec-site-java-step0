-- Seed data: products and product_images (H2 only, idempotent)

INSERT INTO products (product_id, name, description, price, deleted, created_at, updated_at)
SELECT 1, 'ダークインディゴ・ストレートデニム', '上品な深みのあるインディゴブルーが魅力のストレートデニム。
14オンスのセルビッジデニムを使用し、履くほどに身体になじむ質感がたまらない。
ジャケットにもTシャツにも相性バツグンで、オン・オフどちらのスタイルにもフィット。', 12800.00, FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM products WHERE product_id = 1);

INSERT INTO products (product_id, name, description, price, deleted, created_at, updated_at)
SELECT 2, 'クラシック・ライトブルーデニム', '軽やかな色味と柔らかな穿き心地が特徴のライトブルーデニム。
ウォッシュ加工による自然な色落ちがカジュアルスタイルに抜け感をプラス。
春夏のコーデにもピッタリで、ロールアップして履けばさらに爽やかにキマる。', 9900.00, FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM products WHERE product_id = 2);

INSERT INTO products (product_id, name, description, price, deleted, created_at, updated_at)
SELECT 3, 'ヴィンテージ・フェードスリムジーンズ', '絶妙な色落ちとシワ感がこなれ感を演出するスリムフィットジーンズ。
ストレッチ素材をブレンドしており、動きやすさも抜群。
スニーカーにもブーツにも合わせやすい万能デニムで、1本持っておけば間違いなし！', 13200.00, FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM products WHERE product_id = 3);

INSERT INTO products (product_id, name, description, price, deleted, created_at, updated_at)
SELECT 4, 'ホワイトプレーンTシャツ', 'コットン100%のシンプルな無地Tシャツ。軽くて肌触りが良く、オールシーズン活躍するベーシックアイテム。インナーとしてもアウターとしても使えるユニバーサルな一枚。', 4500.00, FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM products WHERE product_id = 4);

INSERT INTO products (product_id, name, description, price, deleted, created_at, updated_at)
SELECT 5, 'ブラックヘビーウェイトTシャツ', '6.2オンスの厚手コットンを使用したヘビーウェイトT。しっかりとした生地感で型崩れしにくく、洗濯を重ねるほど味が出るタフな一枚。ユニセックスなシルエット。', 5500.00, FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM products WHERE product_id = 5);

INSERT INTO products (product_id, name, description, price, deleted, created_at, updated_at)
SELECT 6, 'グレーポケットTシャツ', '左胸にポケットを配したリラックス感のある無地Tシャツ。杢グレーの落ち着いた色味はどんなボトムスとも相性抜群。ゆとりあるシルエットで快適な着心地。', 4200.00, FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM products WHERE product_id = 6);

INSERT INTO products (product_id, name, description, price, deleted, created_at, updated_at)
SELECT 7, 'ネイビーボーダーTシャツ', 'フランス・バスクをルーツに持つマリン風ボーダーTシャツ。ネイビーと白の細かいボーダーがクラシカルな雰囲気を演出。ジーンズとの相性が特に良い。', 5900.00, FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM products WHERE product_id = 7);

INSERT INTO products (product_id, name, description, price, deleted, created_at, updated_at)
SELECT 8, 'オーバーサイズグラフィックTシャツ', 'ドロップショルダーのオーバーサイズシルエットにヴィンテージ風グラフィックをプリント。ルーズな着こなしが今季のムードにマッチ。ショーツやスキニーデニムとの組み合わせがおすすめ。', 6900.00, FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM products WHERE product_id = 8);

INSERT INTO products (product_id, name, description, price, deleted, created_at, updated_at)
SELECT 9, 'ホワイトオックスフォードシャツ', '上質なコットン100%のオックスフォード生地を使用した定番シャツ。清潔感のあるホワイトは、ジーンズからスラックスまで幅広くコーディネートできる万能アイテム。形態安定加工済み。', 8900.00, FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM products WHERE product_id = 9);

INSERT INTO products (product_id, name, description, price, deleted, created_at, updated_at)
SELECT 10, 'ネイビーストライプシャツ', 'ネイビーとホワイトのクリーンなストライプ柄シャツ。ビジネスカジュアルにもオフスタイルにも対応できる汎用性の高い一枚。ポプリン素材で春夏の着用に最適。', 9800.00, FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM products WHERE product_id = 10);

INSERT INTO products (product_id, name, description, price, deleted, created_at, updated_at)
SELECT 11, 'ライトブルーリネンシャツ', '天然リネン100%の軽やかなシャツ。吸湿性・速乾性に優れ、蒸し暑い季節でも快適に着用できる。ライトブルーの涼しげな色味がサマースタイルを格上げ。', 9500.00, FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM products WHERE product_id = 11);

INSERT INTO products (product_id, name, description, price, deleted, created_at, updated_at)
SELECT 12, 'クルーネックスウェット・グレー', '裏毛素材の定番クルーネックスウェット。毛玉になりにくい高密度ニットを採用し、長く愛用できる一枚。ミックスグレーのシンプルな佇まいがあらゆるコーデに馴染む。', 8900.00, FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM products WHERE product_id = 12);

INSERT INTO products (product_id, name, description, price, deleted, created_at, updated_at)
SELECT 13, 'ジップアップパーカー・ブラック', 'フロントジップのオーソドックスなパーカー。厚手の裏起毛素材で秋冬の防寒に最適。大きめのカンガルーポケットと深めのフードが実用的。インナーとしてもアウターとしても活躍。', 12800.00, FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM products WHERE product_id = 13);

INSERT INTO products (product_id, name, description, price, deleted, created_at, updated_at)
SELECT 14, 'プルオーバーパーカー・オートミール', '温かみのあるオートミールカラーのプルオーバーパーカー。柔らかな肌触りとゆったりとしたシルエットがリラックスタイムにぴったり。シンプルな刺繍ロゴがさりげないアクセント。', 11800.00, FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM products WHERE product_id = 14);

INSERT INTO products (product_id, name, description, price, deleted, created_at, updated_at)
SELECT 15, 'コーデュロイシャツジャケット・ブラウン', 'コーデュロイ素材を使ったシャツとジャケットの中間アイテム。秋冬の羽織りに最適で、インナーのTシャツやニットを選ばない。ブラウンの深みある色味が上品な印象を与える。', 18800.00, FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM products WHERE product_id = 15);

INSERT INTO products (product_id, name, description, price, deleted, created_at, updated_at)
SELECT 16, 'テーラードジャケット・ネイビー', '上質なウールブレンド生地を使ったテーラードジャケット。シングルブレスト・2ボタンの正統派シルエット。スラックスとのセットアップ使いはもちろん、デニムへのジャケパンスタイルにも対応。', 28000.00, FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM products WHERE product_id = 16);

INSERT INTO products (product_id, name, description, price, deleted, created_at, updated_at)
SELECT 17, 'ウールチェスターコート・キャメル', 'ウール60%混紡のリッチな質感が魅力のチェスターコート。キャメルカラーは秋冬に最も映える色として人気。すっきりとしたIラインシルエットでスタイルアップ効果抜群。', 45000.00, FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM products WHERE product_id = 17);

INSERT INTO products (product_id, name, description, price, deleted, created_at, updated_at)
SELECT 18, 'フリースジャケット・グリーン', '軽量で保温性に優れたポーラーフリース素材のジャケット。アウトドアシーンからタウンユースまで幅広く活躍。フォレストグリーンの落ち着いた色味がナチュラルスタイルにマッチ。', 9900.00, FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM products WHERE product_id = 18);

INSERT INTO products (product_id, name, description, price, deleted, created_at, updated_at)
SELECT 19, 'チノパンツ・ベージュ', 'コットンツイル素材の定番チノパンツ。ストレートシルエットで脚のラインを自然に整える。ベージュは最もコーディネートしやすいカラーで、オン・オフ問わず重宝するアイテム。', 9900.00, FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM products WHERE product_id = 19);

INSERT INTO products (product_id, name, description, price, deleted, created_at, updated_at)
SELECT 20, 'スラックス・グレー', 'ウールライクな素材感で高見えするテーパードスラックス。センタープレスが入りシャープな印象を演出。ジャケットやシャツとのフォーマルスタイルはもちろん、スニーカーを合わせたリラックスコーデにも対応。', 14800.00, FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM products WHERE product_id = 20);

INSERT INTO products (product_id, name, description, price, deleted, created_at, updated_at)
SELECT 21, 'ワイドカーゴパンツ・オリーブ', 'ワイドシルエットに大型サイドポケットを備えたカーゴパンツ。オリーブカラーがミリタリーテイストを高めつつ、都会的なスタイリングにも馴染む。機能性とトレンド感を両立。', 13800.00, FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM products WHERE product_id = 21);

INSERT INTO products (product_id, name, description, price, deleted, created_at, updated_at)
SELECT 22, 'リネンワイドパンツ・ホワイト', 'リネン素材のナチュラルで涼感あるワイドパンツ。ウエストはゴム仕様でストレスフリーな着用感。ホワイトカラーはサマーシーズンの主役として、クリーンなスタイリングを実現。', 12800.00, FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM products WHERE product_id = 22);

INSERT INTO products (product_id, name, description, price, deleted, created_at, updated_at)
SELECT 23, 'ショートパンツ・ネイビー', '膝上丈のショートパンツ。コットンリップストップ素材で軽量かつ耐久性に優れる。ネイビーのシンプルなデザインはボーダーTやシャツと相性抜群。夏のリゾートスタイルにもおすすめ。', 7900.00, FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM products WHERE product_id = 23);

INSERT INTO products (product_id, name, description, price, deleted, created_at, updated_at)
SELECT 24, 'ケーブルニットセーター・クリーム', 'ウール混の立体的なケーブル編みが美しいセーター。クリームカラーは肌馴染みが良く、顔周りを明るく見せる効果も。程よくボリュームのあるシルエットが秋冬のスタイリングを格上げ。', 18800.00, FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM products WHERE product_id = 24);

INSERT INTO products (product_id, name, description, price, deleted, created_at, updated_at)
SELECT 25, 'モックネックリブニット・ブラック', 'タートルより低めのモックネックとリブ編みが特徴のフィットニット。身体のラインを程よく拾うスリムシルエットで、コートやジャケットのインナーとしても機能的。オールシーズン対応。', 15800.00, FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM products WHERE product_id = 25);

INSERT INTO products (product_id, name, description, price, deleted, created_at, updated_at)
SELECT 26, 'レザーベルト・ブラウン', '本革を使用した上質なレザーベルト。シンプルなスクエアバックルがどんなコーデにも合わせやすい。裏面はスエード仕様で高級感あり。チノパンツやジーンズとの相性が抜群。', 5900.00, FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM products WHERE product_id = 26);

INSERT INTO products (product_id, name, description, price, deleted, created_at, updated_at)
SELECT 27, 'キャンバストートバッグ', '10オンスの厚手キャンバス素材のトートバッグ。A4サイズが楽に入る収納力と、シンプルなデザインが使い勝手抜群。エコバッグとしてもデイリーバッグとしても長く愛用できる定番品。', 7900.00, FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM products WHERE product_id = 27);

INSERT INTO products (product_id, name, description, price, deleted, created_at, updated_at)
SELECT 28, 'レザービジネスウォレット', '本牛革を使用した薄型二つ折り財布。カードポケット8枚分と札入れ、小銭入れを備えたコンパクトな設計。ブラックの洗練されたデザインはビジネスシーンでの使用に最適。', 12800.00, FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM products WHERE product_id = 28);

INSERT INTO products (product_id, name, description, price, deleted, created_at, updated_at)
SELECT 29, 'バックパック・ブラック', '15インチノートPCが収納できるクッション付きPCスリーブを内蔵したバックパック。撥水加工のナイロン素材で急な雨にも対応。シンプルなデザインはビジネスからデイリーまで幅広く対応。', 22000.00, FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM products WHERE product_id = 29);

INSERT INTO products (product_id, name, description, price, deleted, created_at, updated_at)
SELECT 30, 'ニットキャップ・グレー', 'ウールアクリル混紡のベーシックなニットキャップ。伸縮性のあるリブ編みが頭にフィットし、防寒性も高い。シンプルなデザインは男女問わず使えるユニセックスアイテム。', 3900.00, FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM products WHERE product_id = 30);

INSERT INTO products (product_id, name, description, price, deleted, created_at, updated_at)
SELECT 31, 'ウールキャスケット・ネイビー', 'ウールブレンドのクラシックなキャスケット（ハンチングハット）。ツバ付きのデザインがスタイリッシュな印象を演出。ネイビーカラーはシーズンレスに活躍し、コーデのポイントになる。', 7200.00, FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM products WHERE product_id = 31);

INSERT INTO products (product_id, name, description, price, deleted, created_at, updated_at)
SELECT 32, 'レザーキーホルダー', '植物タンニンなめしの本革を使用したシンプルなキーホルダー。使い込むほどに革が育ち、独自のエイジングを楽しめる。カバンの外ポケットに付けてもサマになるミニマルデザイン。', 2400.00, FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM products WHERE product_id = 32);

INSERT INTO products (product_id, name, description, price, deleted, created_at, updated_at)
SELECT 33, 'ウールチェックマフラー', 'ウール85%混紡のクラシックなチェック柄マフラー。グレー×チャコールの落ち着いた配色はどんなコートとも相性抜群。大判サイズで防寒性も高く、秋冬の必需品。', 8900.00, FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM products WHERE product_id = 33);

-- Seed data: product images

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
SELECT 1, 3, '/uploads/products/image33-1.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM product_images WHERE product_image_id = 1);

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
SELECT 3, 1, '/uploads/products/image31-1.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM product_images WHERE product_image_id = 3);

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
SELECT 4, 4, '/uploads/products/image30-1.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM product_images WHERE product_image_id = 4);

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
SELECT 5, 5, '/uploads/products/image29-1.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM product_images WHERE product_image_id = 5);

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
SELECT 6, 6, '/uploads/products/image28-1.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM product_images WHERE product_image_id = 6);

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
SELECT 7, 7, '/uploads/products/image27-1.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM product_images WHERE product_image_id = 7);

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
SELECT 8, 8, '/uploads/products/image26-1.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM product_images WHERE product_image_id = 8);

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
SELECT 9, 9, '/uploads/products/image25-1.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM product_images WHERE product_image_id = 9);

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
SELECT 10, 10, '/uploads/products/image24-1.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM product_images WHERE product_image_id = 10);

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
SELECT 11, 11, '/uploads/products/image23-1.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM product_images WHERE product_image_id = 11);

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
SELECT 12, 12, '/uploads/products/image22-1.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM product_images WHERE product_image_id = 12);

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
SELECT 13, 13, '/uploads/products/image21-1.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM product_images WHERE product_image_id = 13);

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
SELECT 14, 14, '/uploads/products/image20-1.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM product_images WHERE product_image_id = 14);

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
SELECT 15, 15, '/uploads/products/image19-1.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM product_images WHERE product_image_id = 15);

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
SELECT 16, 16, '/uploads/products/image18-1.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM product_images WHERE product_image_id = 16);

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
SELECT 17, 17, '/uploads/products/image17-1.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM product_images WHERE product_image_id = 17);

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
SELECT 18, 18, '/uploads/products/image16-1.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM product_images WHERE product_image_id = 18);

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
SELECT 19, 19, '/uploads/products/image15-1.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM product_images WHERE product_image_id = 19);

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
SELECT 20, 20, '/uploads/products/image14-1.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM product_images WHERE product_image_id = 20);

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
SELECT 21, 21, '/uploads/products/image13-1.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM product_images WHERE product_image_id = 21);

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
SELECT 23, 23, '/uploads/products/image11-1.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM product_images WHERE product_image_id = 23);

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
SELECT 24, 24, '/uploads/products/image10-1.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM product_images WHERE product_image_id = 24);

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
SELECT 25, 25, '/uploads/products/image9-1.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM product_images WHERE product_image_id = 25);

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
SELECT 26, 26, '/uploads/products/image8-1.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM product_images WHERE product_image_id = 26);

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
SELECT 27, 27, '/uploads/products/image7-1.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM product_images WHERE product_image_id = 27);

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
SELECT 28, 28, '/uploads/products/image6-1.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM product_images WHERE product_image_id = 28);

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
SELECT 29, 29, '/uploads/products/image5-1.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM product_images WHERE product_image_id = 29);

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
SELECT 30, 30, '/uploads/products/image4-1.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM product_images WHERE product_image_id = 30);

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
SELECT 31, 31, '/uploads/products/image3-1.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM product_images WHERE product_image_id = 31);

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
SELECT 32, 32, '/uploads/products/image2-1.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM product_images WHERE product_image_id = 32);

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
SELECT 33, 33, '/uploads/products/image1-1.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM product_images WHERE product_image_id = 33);

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
SELECT 34, 33, '/uploads/products/image1-2.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM product_images WHERE product_image_id = 34);

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
SELECT 35, 33, '/uploads/products/image1-3.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM product_images WHERE product_image_id = 35);

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
SELECT 36, 31, '/uploads/products/image3-2.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM product_images WHERE product_image_id = 36);

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
SELECT 37, 31, '/uploads/products/image3-3.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM product_images WHERE product_image_id = 37);

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
SELECT 38, 30, '/uploads/products/image4-2.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM product_images WHERE product_image_id = 38);

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
SELECT 39, 30, '/uploads/products/image4-3.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM product_images WHERE product_image_id = 39);

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
SELECT 40, 29, '/uploads/products/image5-2.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM product_images WHERE product_image_id = 40);

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
SELECT 41, 29, '/uploads/products/image5-3.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM product_images WHERE product_image_id = 41);

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
SELECT 42, 28, '/uploads/products/image6-2.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM product_images WHERE product_image_id = 42);

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
SELECT 43, 27, '/uploads/products/image7-2.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM product_images WHERE product_image_id = 43);

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
SELECT 44, 27, '/uploads/products/image7-3.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM product_images WHERE product_image_id = 44);

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
SELECT 45, 26, '/uploads/products/image8-2.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM product_images WHERE product_image_id = 45);

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
SELECT 46, 25, '/uploads/products/image9-2.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM product_images WHERE product_image_id = 46);

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
SELECT 47, 25, '/uploads/products/image9-3.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM product_images WHERE product_image_id = 47);

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
SELECT 48, 24, '/uploads/products/image10-2.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM product_images WHERE product_image_id = 48);

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
SELECT 49, 24, '/uploads/products/image10-3.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM product_images WHERE product_image_id = 49);

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
SELECT 50, 23, '/uploads/products/image11-2.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM product_images WHERE product_image_id = 50);

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
SELECT 51, 23, '/uploads/products/image11-3.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM product_images WHERE product_image_id = 51);

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
SELECT 52, 22, '/uploads/products/image12-1.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM product_images WHERE product_image_id = 52);

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
SELECT 53, 22, '/uploads/products/image12-2.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM product_images WHERE product_image_id = 53);

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
SELECT 54, 22, '/uploads/products/image12-3.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM product_images WHERE product_image_id = 54);

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
SELECT 55, 21, '/uploads/products/image13-2.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM product_images WHERE product_image_id = 55);

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
SELECT 56, 21, '/uploads/products/image13-3.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM product_images WHERE product_image_id = 56);

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
SELECT 57, 20, '/uploads/products/image14-2.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM product_images WHERE product_image_id = 57);

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
SELECT 58, 20, '/uploads/products/image14-3.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM product_images WHERE product_image_id = 58);

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
SELECT 59, 19, '/uploads/products/image15-2.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM product_images WHERE product_image_id = 59);

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
SELECT 60, 19, '/uploads/products/image15-3.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM product_images WHERE product_image_id = 60);

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
SELECT 61, 18, '/uploads/products/image16-2.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM product_images WHERE product_image_id = 61);

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
SELECT 62, 18, '/uploads/products/image16-3.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM product_images WHERE product_image_id = 62);

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
SELECT 63, 17, '/uploads/products/image17-2.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM product_images WHERE product_image_id = 63);

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
SELECT 64, 17, '/uploads/products/image17-3.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM product_images WHERE product_image_id = 64);

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
SELECT 65, 16, '/uploads/products/image18-2.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM product_images WHERE product_image_id = 65);

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
SELECT 66, 16, '/uploads/products/image18-3.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM product_images WHERE product_image_id = 66);

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
SELECT 67, 15, '/uploads/products/image19-2.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM product_images WHERE product_image_id = 67);

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
SELECT 68, 15, '/uploads/products/image19-3.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM product_images WHERE product_image_id = 68);

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
SELECT 69, 14, '/uploads/products/image20-2.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM product_images WHERE product_image_id = 69);

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
SELECT 70, 14, '/uploads/products/image20-3.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM product_images WHERE product_image_id = 70);

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
SELECT 71, 13, '/uploads/products/image21-2.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM product_images WHERE product_image_id = 71);

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
SELECT 72, 13, '/uploads/products/image21-3.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM product_images WHERE product_image_id = 72);

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
SELECT 73, 12, '/uploads/products/image22-2.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM product_images WHERE product_image_id = 73);

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
SELECT 74, 12, '/uploads/products/image22-3.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM product_images WHERE product_image_id = 74);

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
SELECT 75, 11, '/uploads/products/image23-2.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM product_images WHERE product_image_id = 75);

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
SELECT 76, 11, '/uploads/products/image23-3.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM product_images WHERE product_image_id = 76);

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
SELECT 77, 10, '/uploads/products/image24-2.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM product_images WHERE product_image_id = 77);

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
SELECT 78, 10, '/uploads/products/image24-3.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM product_images WHERE product_image_id = 78);

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
SELECT 79, 9, '/uploads/products/image25-2.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM product_images WHERE product_image_id = 79);

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
SELECT 80, 9, '/uploads/products/image25-3.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM product_images WHERE product_image_id = 80);

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
SELECT 81, 8, '/uploads/products/image26-2.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM product_images WHERE product_image_id = 81);

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
SELECT 82, 8, '/uploads/products/image26-3.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM product_images WHERE product_image_id = 82);

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
SELECT 83, 7, '/uploads/products/image27-2.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM product_images WHERE product_image_id = 83);

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
SELECT 84, 7, '/uploads/products/image27-3.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM product_images WHERE product_image_id = 84);

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
SELECT 85, 6, '/uploads/products/image28-2.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM product_images WHERE product_image_id = 85);

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
SELECT 86, 6, '/uploads/products/image28-3.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM product_images WHERE product_image_id = 86);

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
SELECT 87, 5, '/uploads/products/image29-2.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM product_images WHERE product_image_id = 87);

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
SELECT 88, 5, '/uploads/products/image29-3.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM product_images WHERE product_image_id = 88);

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
SELECT 89, 4, '/uploads/products/image30-2.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM product_images WHERE product_image_id = 89);

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
SELECT 90, 4, '/uploads/products/image30-3.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM product_images WHERE product_image_id = 90);

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
SELECT 91, 1, '/uploads/products/image31-2.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM product_images WHERE product_image_id = 91);

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
SELECT 92, 1, '/uploads/products/image31-3.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM product_images WHERE product_image_id = 92);

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
SELECT 99, 3, '/uploads/products/image33-2.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM product_images WHERE product_image_id = 99);

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
SELECT 100, 3, '/uploads/products/image33-3.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM product_images WHERE product_image_id = 100);

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
SELECT 101, 2, '/uploads/products/image32-1.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM product_images WHERE product_image_id = 101);

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
SELECT 102, 2, '/uploads/products/image32-2.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM product_images WHERE product_image_id = 102);

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
SELECT 103, 2, '/uploads/products/image32-3.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM product_images WHERE product_image_id = 103);
