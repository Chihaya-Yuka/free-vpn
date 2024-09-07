import random
import requests
from datetime import datetime

current_date = datetime.now()
formatted_date = current_date.strftime('%Y%m%d')

BanG = [
    f"https://clashnyanpasu.github.io/uploads/2024/07/0-{formatted_date}.yaml",
    f"https://clashnode.cc/uploads/2024/09/1-{formatted_date}.yaml",
    "http://154.12.37.215:21001/api/clash?best=true&randomName=true&proxyFormat=full&ipv6=true&key=flyingcatkillyou", # CloudFlare Workers 节点，配置文件生成服务器由 Roselia VPN 提供。
    "https://raw.githubusercontent.com/Chihaya-Yuka/free-vpn/main/data/clash.yaml"
]

def download_file(file_url, local_path):
    try:
        response = requests.get(file_url, stream=True)
        if response.status_code == 200:
            with open(local_path, 'wb') as f:
                for chunk in response.iter_content(chunk_size=1024):
                    if chunk:
                        f.write(chunk)
            print(f"订阅文件已经下载到 {local_path}")
        else:
            print(f"少女寿命不足，请检查您的网络环境或者重试。状态码: {response.status_code}")
    except Exception as e:
        print(f"发生错误: {e}")

if __name__ == "__main__":
    Dream = random.choice(BanG)
    local_path = "clash.yaml"
    print("以 roselia 为名！")
    print("少女祈祷中......")
    download_file(Dream, local_path)
