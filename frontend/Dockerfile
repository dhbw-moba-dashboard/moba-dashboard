FROM node:18.20-alpine

RUN mkdir -p /home/node/app/moba_dashboard_frontend/node_modules && chown -R node:node /home/node/app/moba_dashboard_frontend

WORKDIR /home/node/app

COPY package.json .
COPY package-lock.json .
COPY moba_dashboard_frontend/package.json ./moba_dashboard_frontend
COPY moba_dashboard_frontend/package-lock.json ./moba_dashboard_frontend

RUN chown -R node:node /home/node/app

USER node

WORKDIR /home/node/app/moba_dashboard_frontend

RUN npm install

COPY --chown=node:node moba_dashboard_frontend .

RUN npm run build

EXPOSE 3000

ENTRYPOINT [ "npm", "run", "serve" ]